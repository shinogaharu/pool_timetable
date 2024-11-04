package org.example.services;

import org.example.AvailableTime;
import org.example.entities.Client;
import org.example.entities.Reserve;
import org.example.repos.ClientsRepo;
import org.example.repos.ReservesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для организации работы с записями.
 */
@Service
public class ReserveService {

    @Autowired
    private ReservesRepo reservesRepo; // репозиторий записей

    @Autowired
    private ClientsRepo clientsRepo; // репозиторий клиентов

    // Константы определяющие начало и конец времени для записи в бассейн.
    private static final LocalTime SCHEDULE_BEGIN = LocalTime.of(9, 0);
    private static final LocalTime SCHEDULE_END = LocalTime.of(18, 0);

    /**
     * Получение списка записей по конкретной дате и времени.
     * @param date - дата и время.
     * @return список записей.
     */
    public List<Reserve> getReserves(LocalDateTime date) {
        return reservesRepo.findByDatetime(date);
    }

    /**
     * Получение списка записей по данным клиента.
     * @param client - данные клиента.
     * @return список записей.
     */
    public List<Reserve> getReserves(Client client) {
        return reservesRepo.findByClient(client);
    }

    /**
     * Создание записи с проверкой различных условий.
     * @param clientId - id клиента.
     * @param datetime - дата и время для записи.
     * @return информации по созданной записи.
     */
    public Reserve reserve(int clientId, LocalDateTime datetime) {

        LocalTime time = datetime.toLocalTime();

        // Проверка на правильность времени для записи
        if (time.getMinute() != 0){
            throw new RuntimeException("Wrong time for reserve");
        }

        // Проверка на рабочее время бассейна
        if (time.isBefore(SCHEDULE_BEGIN) || time.isAfter(SCHEDULE_END)) {
            throw new RuntimeException("The time for reserve is beyond of working hours");
        }

        // Проверка на свободные места в текущее время
        if (reservesRepo.findByDatetime(datetime).size() >= 10) {
            throw new RuntimeException("Maximum reserves reached for this time");
        }

        // Проверка наличия клиента в базе
        Client client = clientsRepo.findById(clientId).orElseThrow(() -> new RuntimeException("ClientId is not found"));

        // Проверка единичной записи на текущий день
        LocalDateTime startOfDay = datetime.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        List<Reserve> savedReserves = reservesRepo.findByClientAndDatetimeBetween(client, startOfDay, endOfDay);
        if (!savedReserves.isEmpty()) {
            throw  new RuntimeException("The reserve already exists for this day");
        }

        // Создание записи
        Reserve reservation = new Reserve(client, datetime);
        return reservesRepo.save(reservation);
    }

    /**
     * Получение списка доступных мест для записи.
     * @param date - интересующая дата.
     * @return список с записями и количеством свободных мест.
     */
    public List<AvailableTime> getAvailableTime (LocalDate date) {
        List<AvailableTime> availableTimes = new ArrayList<>();
        for (LocalTime time = SCHEDULE_BEGIN; time.isBefore(SCHEDULE_END.plusHours(1)); time = time.plusHours(1)) {
            LocalDateTime timeSlot = date.atTime(time);
            int count = reservesRepo.findByDatetime(timeSlot).size();

            if (10 - count > 0) {
                availableTimes.add(new AvailableTime(timeSlot.toString(), 10 - count));
            }
        }
        return availableTimes;
    }

    /**
     * Удаление записей по идентификатору.
     * @param orderId - id записи
     */
    public void cancel(int orderId) {
        reservesRepo.deleteById(orderId);
    }

}
