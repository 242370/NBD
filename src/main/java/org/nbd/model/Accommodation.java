package org.nbd.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;

@Getter
public class Accommodation implements Serializable {
    @BsonProperty("id")
    private int id;
    @BsonProperty("capacity")
    private double capacity;
    @Setter
    @BsonProperty("pricePerPerson")
    private double pricePerPerson;
    @Setter
    @BsonProperty("rating")
    private int rating;
    @BsonProperty("destination")
    private String destination;

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

    public Accommodation() {

    }

    @Override
    public String toString() {
        return "id: " + getId() + "\n" +
                "capacity: " + getCapacity() + "\n" +
                "pricePerPerson: " + getPricePerPerson() + "\n" +
                "rating: " + getRating() + "\n" +
                "destination: " + getDestination() + "\n";
    }
}
