package org.nbd.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Accommodations")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private final double capacity;
    @Column
    private final double pricePerPerson;
    @Column
    private int rating;
    @Column
    private final String destination;

    public Accommodation(double capacity, double pricePerPerson, int rating, String destination) {
        this.capacity = capacity;
        this.pricePerPerson = pricePerPerson;
        this.rating = rating;
        this.destination = destination;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getPricePerPerson() {
        return pricePerPerson;
    }

    public int getRating() {
        return rating;
    }

    public String getDestination() {
        return destination;
    }
}
