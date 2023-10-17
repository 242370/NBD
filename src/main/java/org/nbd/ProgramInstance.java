package org.nbd;

import org.nbd.model.Accommodation;
import org.nbd.model.Jet;
import org.nbd.model.TravelAgency;

public class ProgramInstance {
    public static void main(String[] args) {
        TravelAgency sharkTours = new TravelAgency(1000);
        try {
            sharkTours.getAccommodationManager().addPlace(10, 1, 2, "werq");
            Accommodation accommodation = sharkTours.getAccommodationManager().getByIndex(0);
            sharkTours.getTransportManager().addJet(150);
            Jet jet = (Jet) sharkTours.getTransportManager().getByIndex(0);
            sharkTours.getTripManager().addTrip(10, "QAZ", jet, accommodation);
            sharkTours.getTransportManager().addLift(150);
            sharkTours.getClientManager().addClient("Rafal", "Cybula", 50);
            sharkTours.getClientManager().addClientWithPet("Adam", "Kruszynski", 50, "Kaczka", "duck", 10);
            sharkTours.getTripManager().addClientToTrip(sharkTours.getClientManager().getByIndex(0), sharkTours.getTripManager().getByIndex(0));
            sharkTours.getTripManager().addClientToTrip(sharkTours.getClientManager().getByIndex(1), sharkTours.getTripManager().getByIndex(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}