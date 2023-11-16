package org.nbd.managers;

import org.nbd.model.Accommodation;
import org.nbd.model.Client;
import org.nbd.model.TransportMean;
import org.nbd.model.Trip;
import org.nbd.repos.TripRepo;

public class TripManager {
    private TripRepo tripRepo;

    public TripManager(TripRepo tripRepo) {
        this.tripRepo = tripRepo;
    }

    public Trip getByID(int id) {
        Trip trip = null;
        try {
            trip = tripRepo.getByID(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return trip;
    }

    public void addTrip(int id, int length, String name, TransportMean transportMean, Accommodation accommodation) {
        tripRepo.add(new Trip(id, length, name, transportMean, accommodation));
    }

    public void addClientToTrip(Client client, Trip trip) {
        this.tripRepo.addClientToTrip(trip, client);
    }

    public void remove(int id) {
        try {
            this.tripRepo.remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}