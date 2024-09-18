package ru.clevertec;

public class Message {
    private final String name;
    public Message(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                '}';
    }
}
