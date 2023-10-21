package org.nbd.repos;

import jakarta.persistence.EntityManager;
import org.nbd.model.Client;
import org.nbd.model.Trip;

import java.util.ArrayList;

public class TripRepo {
    private ArrayList<Trip> trips = new ArrayList<>();
    EntityManager entityManager;

    public TripRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean add(Trip trip)
    {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(trip);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return trips.add(trip);
    }
    public Trip getByID(int index)
    {
        Trip trip = null;
        try {
            entityManager.getTransaction().begin();
            trip = entityManager.find(Trip.class, index);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return trip;
    }
    public int getSize()
    {
        return trips.size();
    }

    public void addClientToTrip(Trip trip, Client client)
    {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(trip);
            trip.addClient(client);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}
