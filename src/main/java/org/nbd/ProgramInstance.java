package org.nbd;

import org.nbd.kafka.Consumer;
import org.nbd.kafka.KafkaManager;
import org.nbd.model.Accommodation;
import org.nbd.repos.AccommodationRepoRedis;

public class ProgramInstance {
    public static void main(String[] args) {
        System.out.println("Welcome to Shark Tours!");

        try {
//            AccommodationRepoRedis repo = new AccommodationRepoRedis(900);
//
//            Accommodation accommodation = new Accommodation(0, 3.0, 5.0, 4, "Stare Poesies");
//
//            System.out.println(accommodation.toString());
//            System.out.println();
//
//            repo.add(accommodation);
//
//            Accommodation newAccommodation = repo.getByID(0);
//
//            System.out.println(newAccommodation.toString());
            Consumer consumer = new Consumer(KafkaManager.topic);
            System.out.println("Thread: " + Thread.currentThread().getId());

            while (true) {
                consumer.read();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}