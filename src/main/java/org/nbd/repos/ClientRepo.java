package org.nbd.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.nbd.model.Client;

import java.util.ArrayList;

public class ClientRepo implements IRepo<Client>{
    EntityManager entityManager;

    public ClientRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void add(Client client)
    {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public Client getByID(int id)
    {
        Client client = null;
        try {
            entityManager.getTransaction().begin();
            client = entityManager.find(Client.class, id);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return client;
    }

    public void remove(int id)
    {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Client.class, id, LockModeType.PESSIMISTIC_WRITE));
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public long getSize()
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(Client.class)));
        return entityManager.createQuery(query).getSingleResult();
    }
}
