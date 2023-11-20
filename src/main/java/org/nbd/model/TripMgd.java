package org.nbd.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;

@Getter
public class TripMgd {
    @BsonProperty("id")
    private final int id;
    @BsonProperty("length")
    private final int length;
    @BsonProperty("name")
    private final String name;
    @Setter
    @BsonProperty("actualWeight")
    private int actualWeight = 0;
    @BsonProperty("transportMean")
    private final TransportMgd transportMean;
    @BsonProperty("accommodation")
    private final Accommodation accommodation;
    @Setter
    @BsonProperty("clients")
    private int clients;

    @BsonCreator
    public TripMgd(@BsonProperty("id") int id, @BsonProperty("length") int length, @BsonProperty("name") String name,
                @BsonProperty("transportMean") TransportMgd transportMean, @BsonProperty("accommodation") Accommodation accommodation) {
        this.id = id;
        this.length = length;
        this.name = name;
        this.transportMean = transportMean;
        this.accommodation = accommodation;
    }

    public int getNumberOfClients() {
        return this.clients;
    }


    public void addClient(Client customer) throws Exception {

        if (clients + 1 > this.accommodation.getCapacity()) {
            throw new Exception("Capacity not enough");
        }

        if (this.actualWeight + customer.getWeight() > this.transportMean.getMaxWeight()) {
            throw new Exception("Weight too much");
        }
        if (customer.hasPet() && !this.transportMean.isPetSupportive()) {
            throw new Exception("Transport not pet supportive");
        }
        this.actualWeight += customer.getWeight();
        clients++;
    }
}
