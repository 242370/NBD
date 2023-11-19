package org.nbd.model;

import org.nbd.model.Jet;

import org.bson.Document;
import org.nbd.model.Lift;
import org.nbd.model.Scooter;

import java.util.Objects;

public class TransportMapper {
    public static TransportMean fromMongoTransport(TransportMgd transportMgd) {
        if (Objects.equals(transportMgd.getType(), "Jet")) {
            return new Jet(transportMgd.getId(), transportMgd.getMaxWeight());
        } else if (Objects.equals(transportMgd.getType(), "Lift")) {
            return new Lift(transportMgd.getId(), transportMgd.getMaxWeight());
        } else {
            return new Scooter(transportMgd.getId(), transportMgd.getMaxWeight());
        }
    }

    public static TransportMgd toMongoTransport(TransportMean transportMean) {
        TransportMgd transportMgd = new TransportMgd(transportMean.getId(), transportMean.getMaxWeight());

        transportMgd.setAvailable(transportMean.isAvailable());
        transportMgd.setType(transportMean.getType());

        return transportMgd;
    }
}
