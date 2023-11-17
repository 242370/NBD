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

        try (ClientRepo repo = new ClientRepo()) {
            Client client = new Client(1, "Rafal", "Cyberbully", 50);
            repo.add(client);
            System.out.println(repo.getByID(1).getLastName());
        } catch (Exception e) {
            System.out.println("oops:\n" + e);
        }
        // TODO: testing env
    }
}