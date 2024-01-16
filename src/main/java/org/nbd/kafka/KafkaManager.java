package org.nbd.kafka;

import lombok.Getter;
import org.nbd.repos.AccommodationRepo;

public class KafkaManager {
    public static String topic = "trips";
    public static String consumers = "consumers";
    public static AccommodationRepo repo = new AccommodationRepo();
    public static int id = 101;
}
