package org.nbd.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Accommodations")
@NoArgsConstructor
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private double capacity;
    @Column
    private double pricePerPerson;
    @Column
    private int rating;
    @Column
    private String destination;

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
