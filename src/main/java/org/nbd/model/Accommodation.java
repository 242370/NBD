package org.nbd.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
public class Accommodation {
    @BsonProperty("id")
    private final int id;
    @BsonProperty("capacity")
    private final double capacity;
    @Setter
    @BsonProperty("pricePerPerson")
    private double pricePerPerson;
    @Setter
    @BsonProperty("rating")
    private int rating;
    @BsonProperty("destination")
    private final String destination;

    @BsonCreator

    public Accommodation(@BsonProperty("id") int id, @BsonProperty("capacity") double capacity,
                         @BsonProperty("pricePerPerson") double pricePerPerson, @BsonProperty("rating") int rating,
                         @BsonProperty("destination") String destination) {
        this.id = id;
        this.capacity = capacity;
        this.pricePerPerson = pricePerPerson;
        this.rating = rating;
        this.destination = destination;
    }
}
