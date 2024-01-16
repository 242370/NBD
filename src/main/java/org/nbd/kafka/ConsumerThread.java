package org.nbd.kafka;

public class ConsumerThread implements Runnable{
    @Override
    public void run() {
        Consumer consumer = new Consumer(KafkaManager.topic);
        System.out.println("Thread: " + Thread.currentThread().getId());

        while (true)
        {
            consumer.read();
        }
    }
}
