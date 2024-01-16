package org.nbd.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.nbd.repos.TripRepo;

import java.util.Collections;
import java.util.Properties;

public class Consumer {
    private KafkaConsumer<String, String> consumer;
    private TripRepo repo = new TripRepo();
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

    public static void main(String[] args) {
        Consumer consumer = new Consumer(KafkaManager.topic);

        while (true) {
            ConsumerRecords<String, String> records = consumer.consumer.poll(0);

            records.forEach(record -> System.out.println("Received: " + record.value()));
        }
    }
}
