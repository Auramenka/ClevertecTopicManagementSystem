package ru.clevertec;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class TopicManagerTest {

    @Test
    public void testTopic() throws InterruptedException {
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

        assertEquals(5, consumer1.getMessageList().size());
        assertEquals(5, consumer2.getMessageList().size());
    }

}