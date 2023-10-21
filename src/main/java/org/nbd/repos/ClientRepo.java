package org.nbd.repos;

import jakarta.persistence.EntityManager;
import org.nbd.model.Client;
import org.nbd.model.Trip;

import java.util.ArrayList;

public class ClientRepo {
    private ArrayList<Client> clients = new ArrayList<>();
    EntityManager entityManager;

    public ClientRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean addClient(Client client)
    {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return clients.add(client);
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

    public int getSize()
    {
        return clients.size();
    }
}
