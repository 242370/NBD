package org.nbd.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.nbd.model.Accommodation;
import org.nbd.model.Trip;
import org.nbd.repos.AccommodationRepo;
import org.nbd.repos.TripRepo;

import java.util.Collections;
import java.util.Properties;

public class Consumer {
    private KafkaConsumer<String, String> consumer;
    private ObjectMapper mapper = new ObjectMapper();

    public Consumer(String topic) {
        this.consumer = this.initConsumer(topic);
    }

    public KafkaConsumer<String, String> initConsumer(String topic) {
        Properties consumerProps = new Properties();

        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaManager.consumers);
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9192,kafka2:9292,kafka3:9392");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singleton(topic));

        return consumer;
    }

    public void read() {
        ConsumerRecords<String, String> records = this.consumer.poll(0);

        records.forEach(record ->
        {
            System.out.println("Received on thread " + Thread.currentThread().getId() + " " + record.value());

            try {
                Accommodation newAccommodation = this.mapper.readValue(record.value(), Accommodation.class);

                KafkaManager.repo.add(newAccommodation);
                System.out.println(KafkaManager.repo.getByID(newAccommodation.getId()).toString());
                KafkaManager.repo.remove(newAccommodation.getId());
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
            }

            this.consumer.commitAsync();
        });
    }

    public void close()
    {
        this.consumer.close();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            ConsumerThread consumerThread = new ConsumerThread();
            Thread thread = new Thread(consumerThread);
            thread.start();
        }
    }
}
