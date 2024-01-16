package org.nbd.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.nbd.model.Client;

import java.util.Properties;

public class Producer {
    private KafkaProducer<String, String> producer;
    private ObjectMapper mapper;
    private PolymorphicTypeValidator validator;

    public Producer() {
        this.producer = this.initProducer();
        this.mapper = new ObjectMapper();
    }

    public KafkaProducer<String, String> initProducer()
    {
        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "kafka1:9192,kafka2:9292,kafka3:9392");
        producerProps.put(ProducerConfig.CLIENT_ID_CONFIG,
                "local");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        producerProps.put(ProducerConfig.ACKS_CONFIG, "0");

        return new KafkaProducer<>(producerProps);
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        Client client = new Client(1, "Rafal", "Cyberbully", 50);

        for(int i = 0 ; i < 10 ; i++)
        {
            String message = "Message: " + i;
            ProducerRecord<String, String> record = new ProducerRecord<>(KafkaManager.topic, message);
            producer.producer.send(record);
            System.out.println(record);
        }

        producer.producer.close();
    }
}
