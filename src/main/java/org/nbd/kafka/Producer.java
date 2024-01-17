package org.nbd.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.nbd.model.Accommodation;
import org.nbd.model.Client;
import org.nbd.model.Jet;
import org.nbd.model.Trip;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Producer {
    private KafkaProducer<String, String> producer;
    private ObjectMapper mapper;

    public Producer() {
        this.producer = this.initProducer();
        this.mapper = new ObjectMapper();
    }

    public KafkaProducer<String, String> initProducer() {
        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "kafka1:9192,kafka2:9292,kafka3:9392");
        producerProps.put(ProducerConfig.CLIENT_ID_CONFIG,
                "local");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        producerProps.put(ProducerConfig.ACKS_CONFIG, "all");
        producerProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        return new KafkaProducer<>(producerProps);
    }

    public void produce(Accommodation accommodation) {
        String accommodationToString = null;
        try {
            accommodationToString = this.mapper.writeValueAsString(accommodation);

            ProducerRecord<String, String> record = new ProducerRecord<>(KafkaManager.topic, accommodation.getDestination(), accommodationToString);

            Future<RecordMetadata> sent = this.producer.send(record);
            RecordMetadata recordMetadata = sent.get();
        } catch (JsonProcessingException | ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        this.producer.close();
    }

    public static void main(String[] args) {
        Producer producer = new Producer();

        for (int i = 100; i < 106; i++) {
            Accommodation accommodation = null;
            if(i % 2 == 0)
            {
                accommodation = new Accommodation(i, 5.0, 10.0, 4, "Zgierz");
            }
            else
            {
                accommodation = new Accommodation(i, 5.0, 10.0, 4, "Sosnowiec");
            }
            producer.produce(accommodation);
        }
//        producer.close();
    }
}
