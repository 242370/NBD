package org.nbd.model;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
public class CashedAccommodation implements Serializable {
    @JsonbProperty("id")
    private final int id;
    @JsonbProperty("capacity")
    private final double capacity;
    @Setter
    @JsonbProperty("pricePerPerson")
    private double pricePerPerson;
    @Setter
    @JsonbProperty("rating")
    private int rating;
    @JsonbProperty("destination")
    private final String destination;

    @JsonbCreator

    public CashedAccommodation(@JsonbProperty("id") int id, @JsonbProperty("capacity") double capacity,
                               @JsonbProperty("pricePerPerson") double pricePerPerson, @JsonbProperty("rating") int rating,
                               @JsonbProperty("destination") String destination) {
        this.id = id;
        this.capacity = capacity;
        this.pricePerPerson = pricePerPerson;
        this.rating = rating;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "CashedAccommodation: " + '\n' +
                "ID: " + this.id + '\n' +
                "Destination: " + this.destination + '\n' +
                "Capacity: " + this.capacity;
    }
}
