package org.nbd.repos;

import jakarta.persistence.EntityManager;
import org.nbd.model.Client;

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

    public Client getByIndex(int index) throws Exception
    {
        if(index >= clients.size() || index < 0)
        {
            throw new Exception("index out of range");
        }
        return clients.get(index);
    }

    public int getSize()
    {
        return clients.size();
    }
}
