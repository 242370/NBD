package org.nbd.model;

import org.nbd.model.Jet;

import org.bson.Document;
import org.nbd.model.Lift;
import org.nbd.model.Scooter;

import java.util.Objects;

public class TransportMapper {
    public static TransportMean fromMongoTransport(TransportMgd transportMgd) {
        if (Objects.equals(transportMgd.getType(), "Jet")) {
            Jet jet = new Jet(transportMgd.getId(), transportMgd.getMaxWeight());
            jet.setAvailable(transportMgd.isAvailable());
            return jet;
        } else if (Objects.equals(transportMgd.getType(), "Lift")) {
            Lift lift = new Lift(transportMgd.getId(), transportMgd.getMaxWeight());
            lift.setAvailable(transportMgd.isAvailable());
            return lift;
        } else {
            Scooter scooter = new Scooter(transportMgd.getId(), transportMgd.getMaxWeight());
            scooter.setAvailable(transportMgd.isAvailable());
            return scooter;
        }
    }

    public static TransportMgd toMongoTransport(TransportMean transportMean) {
        TransportMgd transportMgd = new TransportMgd(transportMean.getId(), transportMean.getMaxWeight());

        transportMgd.setAvailable(transportMean.isAvailable());
        transportMgd.setType(transportMean.getType());

        return transportMgd;
    }
}
