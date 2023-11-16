package org.nbd.managers;

import org.nbd.model.Client;
import org.nbd.repos.ClientRepo;

public class ClientManager {
    private ClientRepo clientRepo;

    public ClientManager(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Client getByID(int id) {
        Client client = null;
        try {
            client = clientRepo.getByID(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return client;
    }

    public void addClient(int id, String firstName, String lastName, int weight) throws Exception {
        if (weight < 1) {
            throw new Exception("Weight below zero");
        }
        Client client = new Client(id, firstName, lastName, weight);
        this.clientRepo.add(client);
    }

    public void addClientWithPet(int id, String firstName, String lastName, int weight, String petName, String petSpecies, int petWeight) throws Exception {
        if (weight < 1 || petWeight < 1) {
            throw new Exception("Weight below zero");
        }
        Client client = new Client(id, firstName, lastName, weight);
        client.addPet(petName, petSpecies, petWeight);
        clientRepo.add(client);
    }

    public void remove(int id) {
        try {
            this.clientRepo.remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}