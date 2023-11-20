package org.nbd.model;

public class TripMapper {
    public static Trip fromMongoTrip(TripMgd tripMgd) {
        return new Trip(tripMgd.getId(), tripMgd.getLength(), tripMgd.getName(), TransportMapper.fromMongoTransport(tripMgd.getTransportMean())
                ,tripMgd.getAccommodation());
    }

    public static TripMgd toMongoTrip(Trip trip) {
        return new TripMgd(trip.getId(), trip.getLength(), trip.getName(), TransportMapper.toMongoTransport(trip.getTransportMean())
                ,trip.getAccommodation());
    }
}
