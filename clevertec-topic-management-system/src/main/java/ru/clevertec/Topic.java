package ru.clevertec;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Topic {
    private final String name;
    private final List<Message> messages = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    public Topic(String name) {
        this.name = name;
    }

    public void publishMessage(Message message) {
        lock.lock();
        try {
            messages.add(message);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Message consumeMessage(int consumerIndex) throws InterruptedException {
        lock.lock();
        try {
            while (consumerIndex + 1 >= messages.size()) {
                condition.await();
            }
            return messages.get(consumerIndex);
        } finally {
            lock.unlock();
        }
    }
}
