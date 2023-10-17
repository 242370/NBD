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
    public Trip getByIndex(int index) throws Exception
    {
        if(index >= trips.size() || index < 0)
        {
            throw new Exception("index out of range");
        }
        return trips.get(index);
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
