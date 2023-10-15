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
            sharkTours.getTransportManager().addJet(100);
            Jet jet = (Jet) sharkTours.getTransportManager().getByIndex(0);
            sharkTours.getTripManager().addTrip(10, "QAZ", jet, accommodation);
            sharkTours.getTripManager().addClient(sharkTours.getTripManager().getByIndex(0), "Rafal", "Cybula", 10);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}