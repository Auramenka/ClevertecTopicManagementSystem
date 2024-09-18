package ru.clevertec;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Consumer extends Thread {
    private final Topic topic;
    private final List<Message> messageList = new ArrayList<>();
    private final CountDownLatch countDownLatch;
    private int consumerIndex = 0;
    public Consumer(Topic topic, CountDownLatch countDownLatch) {
        this.topic = topic;
        this.countDownLatch = countDownLatch;
    }
    public List<Message> getMessageList() {
        return messageList;
    }

    @Override
    public void run() {
        try {
            while (countDownLatch.getCount() > 0) {
                Message message = topic.consumeMessage(consumerIndex);
                messageList.add(message);
                consumerIndex++;
                countDownLatch.countDown();
                System.out.println("Consumed - " + message.getName());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
