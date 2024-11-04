package org.example.entities;

import javax.persistence.*;

/**
 * Класс клиента бассейна.
 */

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId; // id клиента

    private String name; // ФИО киента
    private String phone; // Мобильный номер клиента
    private String email; // Электронная почта клиента

    // Конструкторы
    public Client() {}

    public Client(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Геттеры, сеттеры
    public int getId() {
        return clientId;
    }

    public void setId(int id) {
        this.clientId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
