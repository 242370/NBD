package org.nbd.model;

import com.datastax.oss.driver.api.mapper.annotations.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity(defaultKeyspace = "trips_DB")
@CqlName("Accommodation")
@PropertyStrategy(mutable = false)
public class Accommodation {
    @PartitionKey
    private int id;
    private final double capacity;
    private final double pricePerPerson;
    private int rating;
    @ClusteringColumn
    private final String destination;

    public Accommodation(int id, String destination, double capacity, double pricePerPerson, int rating) {
        this.id = id;
        this.destination = destination;
        this.capacity = capacity;
        this.pricePerPerson = pricePerPerson;
        this.rating = rating;
    }
}
