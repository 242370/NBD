package org.nbd.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Trip {
    @BsonProperty("id")
    private int id;
    @BsonProperty("length")
    private int length;
    @BsonProperty("name")
    private String name;
    @Setter
    @BsonProperty("actualWeight")
    private int actualWeight = 0;

    @Setter
    private int clients = 0;
    @BsonProperty("transportMean")
    TransportMean transportMean;
    Accommodation accommodation;

    public Trip(@BsonProperty("id") int id, @BsonProperty("length") int length, @BsonProperty("name") String name,
                @BsonProperty("transportMean") TransportMean transportMean, Accommodation accommodation) {
        this.id = id;
        this.length = length;
        this.name = name;
        this.transportMean = transportMean;
        this.accommodation = accommodation;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public int getActualWeight() {
        return actualWeight;
    }

    public TransportMean getTransportMean() {
        return transportMean;
    }

    public Accommodation getAccommodation() {
        return accommodation;
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
        if (customer.hasPet() && this.transportMean.isPetSupportive()) {
            throw new Exception("Transport not pet supportive");
        }
        this.actualWeight += customer.getWeight();
        clients++;
    }
}