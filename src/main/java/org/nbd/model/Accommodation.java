package org.nbd.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
public class Accommodation {
    @BsonProperty("capacity")
    private final double capacity;
    @Setter
    @BsonProperty("pricePerPerson")
    private final double pricePerPerson;
    @Setter
    @BsonProperty("rating")
    private int rating;
    @BsonProperty("destination")
    private final String destination;

    public Accommodation(double capacity, double pricePerPerson, int rating, String destination) {
        this.capacity = capacity;
        this.pricePerPerson = pricePerPerson;
        this.rating = rating;
        this.destination = destination;
    }
}
