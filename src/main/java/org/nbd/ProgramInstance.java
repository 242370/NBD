package org.nbd;

import org.nbd.model.Accommodation;
import org.nbd.model.CashedAccommodation;
import org.nbd.model.Client;
import org.nbd.model.TravelAgency;
import org.nbd.repos.AccommodationRepoRedis;

public class ProgramInstance {
    public static void main(String[] args) {
        TravelAgency sharkTours = new TravelAgency(1000);
        System.out.println("Welcome to Shark Tours!");

        try {
            AccommodationRepoRedis repo = new AccommodationRepoRedis(900);

            Accommodation accommodation = new Accommodation(0, 3.0, 5.0, 4, "Stare Poesies");

            System.out.println(accommodation.toString());
            System.out.println();

            repo.add(accommodation);

            Accommodation newAccommodation = repo.getByID(0);

            System.out.println(newAccommodation.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}