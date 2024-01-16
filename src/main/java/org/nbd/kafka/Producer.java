package org.nbd.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.nbd.model.Accommodation;
import org.nbd.model.Client;
import org.nbd.model.Jet;
import org.nbd.model.Trip;

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

    public void produce(Trip trip)
    {
        String tripToString = null;
        try {
            tripToString = this.mapper.writeValueAsString(trip);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }

        ProducerRecord<String, String> record = new ProducerRecord<>(KafkaManager.topic, tripToString);
        this.producer.send(record);
    }

    public void close()
    {
        this.producer.close();
    }

    public static void main(String[] args) {
        Producer producer = new Producer();

        Accommodation accommodation = new Accommodation(1, 5.0, 10.0, 4, "Zgierz");
        Jet jet = new Jet(1, 100);
        Trip trip = new Trip(1, 7, "Akacje pod Gruszą", jet, accommodation);

        producer.produce(trip);

        producer.close();
    }
}
