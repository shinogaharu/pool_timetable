package org.example.repos;

import org.example.entities.Client;
import org.example.entities.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Репозиторий для работы с базой данных записей.
 */
@Repository
public interface ReservesRepo extends JpaRepository<Reserve, Integer> {
    // Создание SQL запросов на основе имени функции.

    /**
     * Поиск записей по дате и времени.
     *
     * @param dateTime - дата и время дла поиска.
     * @return список записей по текущей дате и времени.
     */
    List<Reserve> findByDatetime(LocalDateTime dateTime);

    /**
     * Поиск записей по информации о клиенте.
     *
     * @param client - данные клиента для поиска.
     * @return список записей по данному клиенту.
     */
    List<Reserve> findByClient(Client client);

    /**
     * Поиск записей по информации о клиенте в конкретном временном интервале.
     *
     * @param client - данные клиента для поиска.
     * @param start  - левая граница интервала времени.
     * @param end    - правая граница интервала времени.
     * @return список записей в заданном интервале времени.
     */
    List<Reserve> findByClientAndDatetimeBetween(Client client, LocalDateTime start, LocalDateTime end);
}
