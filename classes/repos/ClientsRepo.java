package org.example.repos;

import org.example.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с базой данных клиентов.
 */
@Repository
public interface ClientsRepo extends JpaRepository<Client, Integer> {
    // Создание SQL запросов на основе имени функции.

    /**
     * Поиск клиентов по имени.
     *
     * @param name - ФИО клиента для поиска.
     * @return список клиентов с именем name.
     */
    List<Client> findByName(String name);
}
