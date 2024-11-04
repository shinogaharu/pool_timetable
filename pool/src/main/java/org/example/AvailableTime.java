package org.example;

/**
 * Класс для описания свободного места для записи.
 */
public class AvailableTime {

    private String time; // Время
    private int count; // Количество свободных мест

    // Конструктор
    public AvailableTime (String time, int count) {
        this.time = time;
        this.count = count;
    }

    // Геттеры
    public String getTime() {
        return time;
    }

    public int getCount() {
        return count;
    }
}
