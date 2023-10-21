package org.nbd.repos;

import jakarta.persistence.EntityManager;
import org.nbd.model.Accommodation;
import org.nbd.model.Trip;

import java.util.ArrayList;

public class AccommodationRepo {

    private ArrayList<Accommodation> accommodations = new ArrayList<>();

    EntityManager entityManager;

    public AccommodationRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean add(Accommodation hotel)
    {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(hotel);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return accommodations.add(hotel);
    }

    public Accommodation getByID(int id)
    {
        Accommodation accommodation = null;
        try {
            entityManager.getTransaction().begin();
            accommodation = entityManager.find(Accommodation.class, id);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return accommodation;
    }

    public int getSize()
    {
        return accommodations.size();
    }
}
