package org.nbd.model;

import org.nbd.managers.AccommodationManager;
import org.nbd.managers.ClientManager;
import org.nbd.managers.TransportManager;
import org.nbd.managers.TripManager;
import org.nbd.repos.AccommodationRepo;
import org.nbd.repos.ClientRepo;
import org.nbd.repos.TransportRepo;
import org.nbd.repos.TripRepo;

public class TravelAgency {
    private double account;
    private final TransportManager transportManager = new TransportManager(new TransportRepo());
    private final AccommodationManager accommodationManager = new AccommodationManager(new AccommodationRepo());
    private final TripManager tripManager = new TripManager(new TripRepo());

    private final ClientManager clientManager = new ClientManager(new ClientRepo());

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

    public ClientManager getClientManager() {
        return clientManager;
    }
}