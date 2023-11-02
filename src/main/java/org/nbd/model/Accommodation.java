package org.nbd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Accommodation {
    private final double capacity;
    @Setter
    private final double pricePerPerson;
    @Setter
    private int rating;
    private final String destination;

    public Accommodation(double capacity, double pricePerPerson, int rating, String destination) {
        this.capacity = capacity;
        this.pricePerPerson = pricePerPerson;
        this.rating = rating;
        this.destination = destination;
    }
}
