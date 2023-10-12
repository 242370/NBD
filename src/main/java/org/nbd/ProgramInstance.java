package org.nbd;

import org.nbd.model.Accommodation;
import org.nbd.model.Jet;
import org.nbd.model.TravelAgency;

public class ProgramInstance {
    public static void main(String[] args) {
        TravelAgency sharkTours = new TravelAgency(1000);
        // Accommodation accommodation = new Accommodation(10, 1, 2, "wer");
        //Jet jet = new Jet(15);
        try {
            sharkTours.getAccommodationManager().addPlace(10, 1, 2, "wer");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());

        }

    }
}