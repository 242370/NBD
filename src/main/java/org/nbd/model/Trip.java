package org.nbd.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Trips")
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column
    private int length;
    @Column
    private String name;
    @Column
    private int actualWeight = 0;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "trip_id")
    private List<Client> clients = new ArrayList<>();
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    TransportMean transportMean;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
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
