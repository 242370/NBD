package org.nbd.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.nbd.model.TransportMean;

import java.util.ArrayList;

public class TransportRepo implements IRepo<TransportMean> {

    EntityManager entityManager;

    public TransportRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void add(TransportMean transportMean) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(transportMean);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public TransportMean getByID(int id) {
        TransportMean transportMean = null;
        try {
            entityManager.getTransaction().begin();
            transportMean = entityManager.find(TransportMean.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return transportMean;
    }

    public void remove(int id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(TransportMean.class, id, LockModeType.PESSIMISTIC_WRITE));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public long getSize() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(TransportMean.class)));
        return entityManager.createQuery(query).getSingleResult();
    }
}
