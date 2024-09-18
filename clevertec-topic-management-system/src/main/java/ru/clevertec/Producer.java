package ru.clevertec;

public class Producer extends Thread {
    private final Topic topic;

    public Producer(Topic topic) {
        this.topic = topic;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Message message = new Message("Message " + i);
            topic.publishMessage(message);
            System.out.println("Produced message - " + message.getName());

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
