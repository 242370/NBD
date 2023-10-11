package org.nbd;

import org.nbd.model.TravelAgency;

public class ProgramInstance {
    public static void main(String[] args) {
        TravelAgency sharkTours = new TravelAgency(1000);

        System.out.println("Welcome to Shark Tours");
        System.out.println("Adding a transport mean");
        sharkTours.getTransportManager().addJet(100, 1);
        System.out.println("Added a Jet with " + sharkTours.getTransportManager().getByIndex(0).isPetSupportive());
        System.out.println("Adding a Trip");
        try {
            sharkTours.getAccommodationManager().addPlace(10, 10, 4, "wer");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sharkTours.getTripManager().addTrip(10, "qwe", sharkTours.getTransportManager().getByIndex(0),
                sharkTours.getAccommodationManager().getByIndex(0));
        System.out.println("Adding client to trip");
        boolean flag = sharkTours.addClient(sharkTours.getTripManager().getByIndex(0), "ert", "rty", 10);
        System.out.println(flag);
        System.out.println(sharkTours.getAccount());
    }
}