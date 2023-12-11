package org.nbd;

import org.nbd.model.CashedAccommodation;
import org.nbd.model.Client;
import org.nbd.model.TravelAgency;
import org.nbd.repos.AccommodationRepoRedis;

public class ProgramInstance {
    public static void main(String[] args) {
        TravelAgency sharkTours = new TravelAgency(1000);
        System.out.println("Welcome to Shark Tours!");

        try {
            AccommodationRepoRedis repo = new AccommodationRepoRedis();

            CashedAccommodation accommodation = new CashedAccommodation(0, 3.0, 5.0, 4, "Stare Poesies");

            System.out.println(accommodation.toString());
            System.out.println();

            repo.putInCache(accommodation);

            CashedAccommodation newAccommodation = repo.getFromCache(0);

            System.out.println(newAccommodation.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}