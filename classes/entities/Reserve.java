package org.example.entities;

import javax.persistence.*;

import java.time.LocalDateTime;

/**
 * Класс записи в бассейн.
 */
@Entity
@Table(name = "reserves")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reserveId; // id записи

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client; // Сущность клиента для получения информации по клиенту через id записи

    private LocalDateTime datetime; // Дата и время записи

    // Конструкторы
    public Reserve() {
    }

    public Reserve(Client client, LocalDateTime datetime) {
        this.client = client;
        this.datetime = datetime;
    }

    // Геттеры, сеттеры
    public int getId() {
        return reserveId;
    }

    public void setId(int id) {
        this.reserveId = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}
