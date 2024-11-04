package org.example.services;

import org.example.entities.Client;
import org.example.repos.ClientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Сервис для организации работы с классом клиентов.
 */
@Service
public class ClientService {

    @Autowired
    private ClientsRepo clientsRepo; // Репозиторий клиентов

    /**
     * Получение списка всех клиентов.
     * @return список клиентов.
     */
    public List<Client> getAllClients() {
        return clientsRepo.findAll();
    }

    /**
     * Получение клиента по id.
     * @param id - уникальный идентификатор клиента.
     * @return null если клиент по данному id не найден, иначе данные по клиенту.
     */
    public Client getClient(int id) {
        return clientsRepo.findById(id).orElse(null);
    }

    /**
     * Получение клиента по ФИО.
     * @param name - ФИО клиента.
     * @return список клиентов с именем name.
     */
    public List<Client> getClientByName(String name) {
        return clientsRepo.findByName(name);
    }

    /**
     * Добавление клиентов в базу.
     * @param client - данные клиента для добавления.
     * @return добавленный клиент.
     */
    public Client addClient(Client client) {
        return clientsRepo.save(client);
    }

    /**
     * Изменение информации по клиенту.
     * @param client - данные клиента для изменения.
     * @return обновленный клиент.
     */
    public Client updateClient(Client client) {
        return clientsRepo.save(client);
    }
}
