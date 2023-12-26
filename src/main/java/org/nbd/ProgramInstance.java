package org.nbd;

import org.nbd.model.Accommodation;
import org.nbd.model.TravelAgency;
import org.nbd.repos.AccommodationRepo;
import org.nbd.repos.CassandraManager;

public class ProgramInstance {
    public static void main(String[] args) {
        TravelAgency sharkTours = new TravelAgency(1000);
        System.out.println("Welcome to Shark Tours!");

        CassandraManager cassandra = new CassandraManager();

        AccommodationRepo repo = new AccommodationRepo();
        Accommodation accommodation = new Accommodation(0, "Zgierz", 5.0, 10.0, 4);

        repo.add(accommodation);
    }
}