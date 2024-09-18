package ru.clevertec;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Topic topic = new Topic("Test topic");

        CountDownLatch countDownLatch = new CountDownLatch(10);

        Producer producer = new Producer(topic);
        Consumer consumer1 = new Consumer(topic, countDownLatch);
        Consumer consumer2 = new Consumer(topic, countDownLatch);

        producer.start();
        consumer1.start();
        consumer2.start();

        countDownLatch.await();
        producer.join();
        consumer1.join();
        consumer2.join();

        System.out.println(consumer1.getMessageList());
        System.out.println(consumer2.getMessageList());
    }
}