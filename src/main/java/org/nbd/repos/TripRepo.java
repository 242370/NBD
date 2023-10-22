package org.nbd.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.nbd.model.Client;
import org.nbd.model.Trip;

import java.util.ArrayList;

public class TripRepo implements IRepo<Trip> {
    EntityManager entityManager;

    public TripRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void add(Trip trip) {
        try {
            entityManager.getTransaction().begin();
            // entityManager.lock(trip.getTransportMean(), LockModeType.PESSIMISTIC_READ);
            if (!trip.getTransportMean().isAvailable()) {
                throw new Exception("Wanted transport mean is unavailable");
            }
            trip.getTransportMean().setAvailable(false);
            entityManager.persist(trip);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public Trip getByID(int id) {
        Trip trip = null;
        try {
            entityManager.getTransaction().begin();
            trip = entityManager.find(Trip.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return trip;
    }

    public void addClientToTrip(Trip trip, Client client) {
        try {
            entityManager.getTransaction().begin();
            Trip updatedTrip = entityManager.merge(trip);
            updatedTrip.addClient(client);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void remove(int id) {
        try {
            entityManager.getTransaction().begin();
            Trip tripToBeRemoved = entityManager.find(Trip.class, id, LockModeType.PESSIMISTIC_WRITE);
            tripToBeRemoved.getTransportMean().setAvailable(true);
            entityManager.remove(tripToBeRemoved);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public long getSize() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(Trip.class)));
        return entityManager.createQuery(query).getSingleResult();
    }
}
