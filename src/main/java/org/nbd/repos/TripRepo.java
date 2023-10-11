package org.nbd.repos;

import org.nbd.model.Trip;

import java.util.ArrayList;

public class TripRepo {
    private ArrayList<Trip> trips = new ArrayList<>();

    public boolean add(Trip trip)
    {
        return trips.add(trip);
    }
    public Trip getByIndex(int index) throws Exception
    {
        if(index >= trips.size() || index < 0)
        {
            throw new Exception("index out of range");
        }
        return trips.get(index);
    }
    public int getSize()
    {
        return trips.size();
    }
}
