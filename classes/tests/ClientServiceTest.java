package org.example.tests;

import org.example.entities.Client;
import org.example.repos.ClientsRepo;
import org.example.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Класс для тестирования работы функций сервиса по работе с клиентами.
 */
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientsRepo clientsRepo;

    @BeforeEach
    void setUp() {
        // Инициализация моков
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Тест для метода getAllClients()
     */
    @Test
    void testGetAllClients() {

        // Создание тестовых клиентов и их список
        Client client1 = new Client("name1" , "123","1@mail.ru");
        Client client2 = new Client("name2" , "321","2@mail.ru");
        List<Client> clients = Arrays.asList(client1, client2);

        // Описание поведения для мока
        when(clientsRepo.findAll()).thenReturn(clients);

        // Вызов тестируемого метода
        List<Client> result = clientService.getAllClients();

        // Проверка результата
        assertEquals(2, result.size());

        // Проверка количества вызовов
        verify(clientsRepo, times(1)).findAll();
    }
}
