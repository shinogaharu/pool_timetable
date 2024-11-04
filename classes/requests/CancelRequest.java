package org.example;

public class CancelRequest {

    /**
     * Класс запроса для отмены записи.
     */
    private int clientId; // id клиента
    private String orderId; // id записи

    // Геттеры, сеттеры
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
