package org.nbd.repos;

import jakarta.persistence.EntityManager;
import org.nbd.model.Accommodation;

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

    public Accommodation getByIndex(int index) throws Exception
    {
        if(index >= accommodations.size() || index < 0)
        {
            throw new Exception("index out of range");
        }
        return accommodations.get(index);
    }

    public int getSize()
    {
        return accommodations.size();
    }
}
