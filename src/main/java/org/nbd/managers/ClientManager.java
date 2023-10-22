package org.nbd.managers;

import org.nbd.model.Client;
import org.nbd.repos.ClientRepo;

public class ClientManager {
    private ClientRepo clientRepo;

    public ClientManager(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Client getByID(int id)
    {
        return clientRepo.getByID(id);
    }
    public void addClient(String firstName, String lastName, int weight) throws Exception
    {
        if(weight < 1)
        {
            throw new Exception("incorrect weight");
        }
        Client client = new Client(firstName, lastName, weight);
        this.clientRepo.add(client);
    }

    public void addClientWithPet(String firstName, String lastName, int weight, String petName, String petSpecies, int petWeight) throws Exception
    {
        if(weight < 1 || petWeight < 1)
        {
            throw new Exception("incorrect weight");
        }
        Client client = new Client(firstName, lastName, weight);
        client.addPet(petName, petSpecies, petWeight);
        clientRepo.add(client);
    }

    public void remove(int id)
    {
        this.clientRepo.remove(id);
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
