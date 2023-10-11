package org.nbd.model;

import java.util.ArrayList;

public class Trip {
    private final int length ;
    private final String name ;
    private int actualWeight = 0 ;
    ArrayList<Client> clients = new ArrayList<>();
    TransportMean transportMean;
    Accommodation accommodation;

    public Trip(int length, String name, TransportMean transportMean, Accommodation accommodation) {
        this.length = length;
        this.name = name;
        this.transportMean = transportMean;
        this.accommodation = accommodation;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public int getActualWeight() {
        return actualWeight;
    }

    public TransportMean getTransportMean() {
        return transportMean;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public Client getClient(int index)
    {
        return clients.get(index);
    }

    public int getNumberOfClients()
    {
        return clients.size();
    }

    public double calculateCost()
    {
        return clients.size() * accommodation.getPricePerPerson();
    }

    public boolean addClient(Client customer)
    {
        if(clients.size() + 1 > this.accommodation.getCapacity())
        {
            return false;
        }

        if(this.actualWeight + customer.getWeight() > this.transportMean.getMaxWeight())
        {
            return false;
        }
        this.actualWeight += customer.getWeight();
        return clients.add(customer);
    }

    public boolean addClientWithPet(Client customer, String petName, String petSpecies, int petWeight)
    {
        if(clients.size() + 1 > this.accommodation.getCapacity())
        {
            return false;
        }

        if(!this.transportMean.isPetSupportive())
        {
            return false;
        }
        if(this.actualWeight + customer.getWeight() + petWeight > this.transportMean.getMaxWeight())
        {
            return false;
        }
        customer.addPet(petName, petSpecies, petWeight);
        this.actualWeight += customer.getWeight();
        return clients.add(customer);
    }
}
