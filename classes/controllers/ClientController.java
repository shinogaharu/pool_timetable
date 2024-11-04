package org.example.controllers;

import org.example.entities.Client;
import org.example.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Контроллер для клиентов бассейна.
 */

@RestController
@RequestMapping("/api/v0/pool/client")
public class ClientController {

    @Autowired
    private ClientService clientService; // Сервис для работы с клиентами

    /**
     * Получение списка всех клиентов.
     *
     * @return ResponseEntity со списком клиентов в необходимом фаормате: id, name.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getClients() {
        // Получение всех клиентов через: Сервис для работы с клиентами -> Репозиторий -> База данных
        List<Map<String, Object>> clients = clientService.getAllClients().stream()
                .map(client -> { // Преобразование каждого клиента в Map
                    Map<String, Object> clientMap = new HashMap<>();
                    clientMap.put("id", client.getId());
                    clientMap.put("name", client.getName());
                    return clientMap;
                }).collect(Collectors.toList()); // Заносим результат в список
        return ResponseEntity.ok(clients); // Возвращаем список клиентов
    }

    /**
     * Поиск клиентов по ФИО.
     *
     * @param name - Имя клиента.
     * @return ResponseEntity со списком клиентов, ФИО которых соответствуют параметру name.
     * Либо статус запроса 404 (не найдено), если клиент не найден.
     */
    @GetMapping("/name")
    public ResponseEntity<List<Client>> getClientsByName(@RequestParam String name) {
        // Получаем список клиентов с необходимым ФИО
        List<Client> clients = clientService.getClientByName(name);
        // Если список не пустой, возвращаем статус 404, иначе список клиентов
        return clients != null ? ResponseEntity.ok(clients) : ResponseEntity.notFound().build();
    }

    /**
     * Поиск клиента по id.
     *
     * @param id - уникальный идентификатор клиента.
     * @return ResponseEntity с данными клиента, номер которого соответствует параметру id в базе данных.
     * Либо статус запроса 404 (не найдено), если клиент не найден.
     */
    @GetMapping("/get")
    public ResponseEntity<Client> getClient(@RequestParam int id) {
        // Получаем клиента с конкретным id
        Client client = clientService.getClient(id);
        // Если клиент не найден, возвращаем статус 404, иначе вернем данные клиента
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    /**
     * Добавление нового клиента в базу.
     *
     * @param client - передается объект клиента с необходимыми данными.
     * @return Добавленный клиента.
     */
    @PostMapping("/add")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    /**
     * Изменение данных клиента в базе.
     *
     * @param client - передается объект клиента с необходимыми данными для перезаписи.
     * @return Обновленный клиент.
     */
    @PostMapping("/update")
    public Client updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }
}
