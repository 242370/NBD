package org.nbd.model;

public class TripMapper {
    public static Trip fromMongoTrip(TripMgd tripMgd) {
        Trip trip = new Trip(tripMgd.getId(), tripMgd.getLength(), tripMgd.getName(), TransportMapper.fromMongoTransport(tripMgd.getTransportMean())
                ,tripMgd.getAccommodation());
        trip.setClients(tripMgd.getNumberOfClients());
        trip.setActualWeight(tripMgd.getActualWeight());
        return trip;
    }

    public static TripMgd toMongoTrip(Trip trip) {
        TripMgd tripMgd = new TripMgd(trip.getId(), trip.getLength(), trip.getName(), TransportMapper.toMongoTransport(trip.getTransportMean())
                ,trip.getAccommodation());
        tripMgd.setClients(trip.getNumberOfClients());
        tripMgd.setActualWeight(trip.getActualWeight());
        return tripMgd;
    }
}
