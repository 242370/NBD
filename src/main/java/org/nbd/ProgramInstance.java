package org.nbd;

import lombok.extern.log4j.Log4j2;
import org.nbd.model.Accommodation;
import org.nbd.model.Jet;
import org.nbd.model.TravelAgency;
import org.nbd.model.Trip;

@Log4j2
public class ProgramInstance {
    public static void main(String[] args) {
        TravelAgency sharkTours = new TravelAgency(1000);
        try {
            sharkTours.getAccommodationManager().addPlace(10, 1, 2, "werq");
            Accommodation accommodation = sharkTours.getAccommodationManager().getByID(1);
            sharkTours.getTransportManager().addJet(150);
            Jet jet = (Jet) sharkTours.getTransportManager().getByID(1);
            sharkTours.getTripManager().addTrip(10, "QAZ", jet, accommodation);
            sharkTours.getTransportManager().addLift(150);
            sharkTours.getClientManager().addClient("Rafal", "Cyberbully", 50);
            sharkTours.getClientManager().addClientWithPet("Adam", "Kruszynski", 50, "Kaczka", "duck", 10);
            sharkTours.getTripManager().addClientToTrip(sharkTours.getClientManager().getByID(1), sharkTours.getTripManager().getByID(1));
            sharkTours.getTripManager().addTrip(10, "xD", sharkTours.getTransportManager().getByID(2), accommodation);
            sharkTours.getTripManager().addClientToTrip(sharkTours.getClientManager().getByID(2), sharkTours.getTripManager().getByID(2));
            Trip trip = sharkTours.getTripManager().getByID(1);
            log.error(trip.getNumberOfClients());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}