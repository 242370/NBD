package org.nbd.model;

public class Accommodation {
    private final double capacity;
    private final double pricePerPerson;
    private int rating;
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
