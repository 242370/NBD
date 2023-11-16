package org.nbd;

import org.nbd.model.Accommodation;
import org.nbd.model.TestDBItem;
import org.nbd.model.TravelAgency;
import org.nbd.repos.AccommodationRepo;

import java.util.ArrayList;

public class ProgramInstance {
    public static void main(String[] args) {
        TravelAgency sharkTours = new TravelAgency(1000);
        System.out.println("Welcome to Shark Tours!");

        try (AccommodationRepo repo = new AccommodationRepo()) {
            Accommodation accommodation = new Accommodation(1, 2.0, 1.0, 3, "qwe");
            repo.add(accommodation);
            Accommodation testDBItemArrayList = repo.getByID(1);
            System.out.println(testDBItemArrayList.getDestination());
        } catch (Exception e) {
            System.out.println("oops:\n" + e);
        }
        // TODO: testing env
    }
}