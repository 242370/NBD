package org.nbd.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@Setter
public class TransportMgd {
    @BsonProperty("id")
    private int id;
    @BsonProperty("maxWeight")
    private int maxWeight;
    @BsonProperty("isAvailable")
    private boolean isAvailable;
    @BsonProperty("type")
    private String type;

    @BsonCreator
    public TransportMgd(@BsonProperty("id") int id, @BsonProperty("maxWeight") int maxWeight) {
        this.id = id;
        this.maxWeight = maxWeight;
        this.isAvailable = true;
    }

    public boolean isPetSupportive() {
        return true;
    }
}
