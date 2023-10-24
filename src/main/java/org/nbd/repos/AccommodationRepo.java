package org.nbd.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.nbd.model.Accommodation;

public class AccommodationRepo implements IRepo<Accommodation> {

    EntityManager entityManager;

    public AccommodationRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void add(Accommodation hotel) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(hotel);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public Accommodation getByID(int id) {
        Accommodation accommodation = null;
        try {
            entityManager.getTransaction().begin();
            accommodation = entityManager.find(Accommodation.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return accommodation;
    }

    public void changePricePerPerson(int id, double newPrice) {
        try {
            entityManager.getTransaction().begin();
            Accommodation updatedAccommodation = entityManager.merge(entityManager.find(Accommodation.class, id, LockModeType.PESSIMISTIC_WRITE));
            updatedAccommodation.setPricePerPerson(newPrice);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void changeRating(int id, int newRating) {
        try {
            entityManager.getTransaction().begin();
            Accommodation updatedAccommodation = entityManager.merge(entityManager.find(Accommodation.class, id, LockModeType.PESSIMISTIC_WRITE));
            updatedAccommodation.setRating(newRating);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void remove(int id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Accommodation.class, id, LockModeType.PESSIMISTIC_WRITE));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public long getSize() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(Accommodation.class)));
        return entityManager.createQuery(query).getSingleResult();
    }
}
