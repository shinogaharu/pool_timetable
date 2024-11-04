package org.example;

public class ReserveRequest {

    /**
     * Класс запроса для создания записи.
     */
    private int clientId; // id клиента
    private String datetime; // Дата и время для записи.

    // Геттеры, сеттеры
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
