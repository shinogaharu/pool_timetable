package org.example.controllers;

import org.example.AvailableTime;
import org.example.CancelRequest;
import org.example.ReserveRequest;
import org.example.entities.Reserve;
import org.example.services.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Контроллер для менеджмента записей в бассейн.
 */
@RestController
@RequestMapping("/api/v0/pool/timetable")
public class ReserveController {

    @Autowired
    private ReserveService reserveService; // Сервис для работы с записями

    /**
     * Получение всех записей на конкретную датуи время.
     * @param date - дата и время для поиска записей в базе данных.
     * @return Список записей.
     */
    @GetMapping("/all")
    public List<Reserve> getAll(@RequestParam String date) {
        // Парсинг строкового параметра в формат LocalDateTime
        LocalDateTime datetime = LocalDateTime.parse(date);
        // Возвращаем через сервис список записей
        return reserveService.getReserves(datetime);
    }

    /**
     * Получение списка доступных записей на конкрутную дату.
     * @param date - дата (в формате дата и время) для поиска записей в базе данных.
     * @return Список свободных записей.
     */
    @GetMapping("/available")
    public List<AvailableTime> getAvailable(@RequestParam String date) {
        // Парсинг строкового параметра в формат LocalDateTime
        LocalDateTime datetime = LocalDateTime.parse(date);
        // Возвращаем через сервис список свободных записей
        return reserveService.getAvailableTime(LocalDate.from(datetime));
    }

    /**
     * Резервирование времени для клиента.
     * @param reserveRequest - специальный класс для получение данных в поставленном формате.
     * @return ResponseEntity с данными о создании записи.
     */
    @PostMapping("/reserve")
    public ResponseEntity<Reserve> reserve(@RequestBody ReserveRequest reserveRequest) {
        // Попытка создания записи
        try {
            Reserve reserve = reserveService.reserve(reserveRequest.getClientId(), LocalDateTime.parse(reserveRequest.getDatetime()));
            return ResponseEntity.status(HttpStatus.CREATED).body(reserve);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Отмена записи по идентификатору клиента и записи.
     * @param cancelRequest - специальный класс для получения данных в поставленном формате.
     */
    @DeleteMapping("/cancel")
    public void cancel(@RequestBody CancelRequest cancelRequest) {
        // Удаление записи
        reserveService.cancel(cancelRequest.getClientId());
    }
}
