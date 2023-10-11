package org.nbd.model;

import org.nbd.managers.AccommodationManager;
import org.nbd.managers.TransportManager;
import org.nbd.managers.TripManager;

public class TravelAgency {
    private double account;
    private final TransportManager transportManager = new TransportManager();
    private final AccommodationManager accommodationManager = new AccommodationManager();
    private final TripManager tripManager = new TripManager();

    public double getAccount() {
        return account;
    }

    public TravelAgency(double account) {
        this.account = account;
    }

    public TransportManager getTransportManager() {
        return transportManager;
    }

    public AccommodationManager getAccommodationManager() {
        return accommodationManager;
    }

    public TripManager getTripManager() {
        return tripManager;
    }

    public boolean addClient(Trip trip, String firstName, String lastName, int weight)
    {
        if(this.tripManager.addClient(trip, firstName, lastName, weight))
        {
            account += trip.getAccommodation().getPricePerPerson();
            return true;
        }
        return false;
    }

    public boolean addClientWithPet(Trip trip, String firstName, String lastName, int weight, String petName, String petSpecies, int petWeight)
    {
        if(this.tripManager.addClientWithPet(trip, firstName, lastName, weight, petName, petSpecies, petWeight))
        {
            account += trip.getAccommodation().getPricePerPerson();
            return true;
        }
        return false;
    }
}
