package org.nbd.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Objects;

@Getter
@Setter
public class TransportMgd {
    @BsonProperty("id")
    private int id;
    @BsonProperty("maxWeight")
    private int maxWeight;
    @BsonProperty("uses")
    private int uses = 0;
    @BsonProperty("type")
    private String type;

    @BsonCreator
    public TransportMgd(@BsonProperty("id") int id, @BsonProperty("maxWeight") int maxWeight) {
        this.id = id;
        this.maxWeight = maxWeight;
    }

    public boolean isAvailable()
    {
        return uses == 0;
    }
    public void setAvailable(boolean available) {
        if(available)
        {
            uses = 0;
        }
        else
        {
            uses++;
        }
    }

    public boolean isPetSupportive() {
        return !Objects.equals(this.getType(), "Scooter");
    }
}
