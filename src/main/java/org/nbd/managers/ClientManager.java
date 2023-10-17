package org.nbd.managers;

import org.nbd.model.Client;
import org.nbd.repos.ClientRepo;

public class ClientManager {
    private ClientRepo clientRepo;

    public ClientManager(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Client getByIndex(int index)
    {
        Client client = null;
        try {
            client = this.clientRepo.getByIndex(index);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return client;
    }
    public boolean addClient(String firstName, String lastName, int weight) throws Exception
    {
        if(weight < 1)
        {
            throw new Exception("incorrect weight");
        }
        Client client = new Client(firstName, lastName, weight);
        return this.clientRepo.addClient(client);
    }

    public void addClientWithPet(String firstName, String lastName, int weight, String petName, String petSpecies, int petWeight) throws Exception
    {
        if(weight < 1 || petWeight < 1)
        {
            throw new Exception("incorrect weight");
        }
        Client client = new Client(firstName, lastName, weight);
        client.addPet(petName, petSpecies, petWeight);
        clientRepo.addClient(client);
    }

//    public List<Accommodation> getAll()
//    {
//        ArrayList<Accommodation> accommodations = new ArrayList<>();
//        for(int i = 0 ; i < this.accommodationRepo.getSize() ; i++)
//        {
//            try {
//                accommodations.add(this.accommodationRepo.getByIndex(i));
//            }
//            catch (Exception e)
//            {
//                System.out.println(e.getMessage());
//            }
//        }
//        return accommodations;
//    }
}