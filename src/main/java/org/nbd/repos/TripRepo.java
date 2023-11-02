package org.nbd.repos;

import org.nbd.model.Client;
import org.nbd.model.Trip;

public class TripRepo implements IRepo<Trip> {


    @Override
    public void add(Trip trip) {
        // TODO: implementation
    }

    @Override
    public Trip getByID(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        // TODO: implementation
        return null;
    }

    @Override
    public void remove(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        // TODO: implementation
    }

    @Override
    public long getSize() {
        // TODO: implementation
        return 0;
    }

    public void addClientToTrip(Trip trip, Client client) {
        // TODO: implementation
    }
}
