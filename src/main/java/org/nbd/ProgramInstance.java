package org.nbd;

import org.nbd.model.Accommodation;
import org.nbd.model.Jet;
import org.nbd.model.TravelAgency;

public class ProgramInstance {
    public static void main(String[] args) {
        TravelAgency sharkTours = new TravelAgency(1000);
        try {
            sharkTours.getAccommodationManager().addPlace(10, 1, 2, "werq");
           sharkTours.getTransportManager().addJet(100);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}