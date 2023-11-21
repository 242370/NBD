package org.nbd;

import org.nbd.model.Accommodation;
import org.nbd.model.Client;
import org.nbd.model.TestDBItem;
import org.nbd.model.TravelAgency;
import org.nbd.repos.AccommodationRepo;
import org.nbd.repos.ClientRepo;

import java.util.ArrayList;

public class ProgramInstance {
    public static void main(String[] args) {
        TravelAgency sharkTours = new TravelAgency(1000);
        System.out.println("Welcome to Shark Tours!");

        try {
            sharkTours.getAccommodationManager().addPlace(1, 2.5, 5.0, 2, "Stare Poesies");
            System.out.println(sharkTours.getAccommodationManager().getByID(1).toString());

            sharkTours.getClientManager().addClient(1, "Rafal", "Cyberbully", 75);
            System.out.println(sharkTours.getClientManager().getByID(1).toString());

            sharkTours.getClientManager().addClientWithPet(2, "Adam", "Kruszynski", 70,
                    "Tytus", "Cat", 5);
            System.out.println(sharkTours.getClientManager().getByID(2).toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}