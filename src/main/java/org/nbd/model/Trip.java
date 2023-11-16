package org.nbd.model;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    @BsonProperty("_id")
    private final int id;
    @BsonProperty("length")
    private int length;
    @BsonProperty("name")
    private String name;
    @BsonProperty("actualWeight")
    private int actualWeight = 0;

    private List<Client> clients = new ArrayList<>();
    TransportMean transportMean;
    Accommodation accommodation;

    public Trip(@BsonProperty("_id") int id, @BsonProperty("length") int length, @BsonProperty("name") String name,
                TransportMean transportMean, Accommodation accommodation) {
        this.id = id;
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

    public Client getClient(int index) {
        return clients.get(index);
    }

    public int getNumberOfClients() {
        return clients.size();
    }

    public double calculateCost() {
        return clients.size() * accommodation.getPricePerPerson();
    }

    public boolean addClient(Client customer) throws Exception {

        if (clients.size() + 1 > this.accommodation.getCapacity()) {
            throw new Exception("Capacity not enough");
        }

        if (this.actualWeight + customer.getWeight() > this.transportMean.getMaxWeight()) {
            throw new Exception("Weight too much");
        }
        if (customer.hasPet() && !this.transportMean.isPetSupportive()) {
            throw new Exception("Transport not pet supportive");
        }
        this.actualWeight += customer.getWeight();
        return clients.add(customer);
    }
}