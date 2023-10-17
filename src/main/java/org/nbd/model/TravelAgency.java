package org.nbd.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.nbd.managers.AccommodationManager;
import org.nbd.managers.ClientManager;
import org.nbd.managers.TransportManager;
import org.nbd.managers.TripManager;
import org.nbd.repos.AccommodationRepo;
import org.nbd.repos.ClientRepo;
import org.nbd.repos.TransportRepo;
import org.nbd.repos.TripRepo;

public class TravelAgency {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TAVEL-PERSISTENCE");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    private double account;
    private final TransportManager transportManager = new TransportManager(new TransportRepo(entityManager));
    private final AccommodationManager accommodationManager = new AccommodationManager(new AccommodationRepo(entityManager));
    private final TripManager tripManager = new TripManager(new TripRepo(entityManager));

    private final ClientManager clientManager = new ClientManager(new ClientRepo(entityManager));

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

    public boolean addClient(Trip trip, Client client) throws Exception {
        if(this.tripManager.addClient(trip, client))
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
