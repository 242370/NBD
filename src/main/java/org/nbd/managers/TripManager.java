package org.nbd.managers;

import org.nbd.model.Accommodation;
import org.nbd.model.Client;
import org.nbd.model.TransportMean;
import org.nbd.model.Trip;
import org.nbd.repos.TripRepo;

import java.util.ArrayList;
import java.util.List;

public class TripManager {
    private TripRepo tripRepo = new TripRepo();

    public Trip getByIndex(int index) {
        Trip trip = null;
        try {
            trip = this.tripRepo.getByIndex(index);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return trip;
    }

    public boolean addTrip(int length, String name, TransportMean transportMean, Accommodation accommodation) {
        if (!transportMean.isAvailable()) {
            return false;
        }
        transportMean.setAvailable(false);
        return tripRepo.add(new Trip(length, name, transportMean, accommodation));
    }

    public boolean addClient(Trip trip, String firstName, String lastName, int weight) {
        return trip.addClient(new Client(firstName, lastName, weight));
    }

    public boolean addClientWithPet(Trip trip, String firstName, String lastName, int weight, String petName, String petSpecies, int petWeight) {
        return trip.addClientWithPet(new Client(firstName, lastName, weight), petName, petSpecies, weight);
    }

    public List<Trip> getAll()
    {
        ArrayList<Trip> trips = new ArrayList<>();
        for(int i = 0 ; i < tripRepo.getSize() ; i++)
        {
            try {
                trips.add(tripRepo.getByIndex(i));
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        return trips;
    }
}
