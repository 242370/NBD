package org.nbd.repos;

import org.nbd.model.Accommodation;

public class AccommodationRepo implements IRepo<Accommodation> {
    public void add(Accommodation hotel) {
        // TODO: implementation
    }

    @Override
    public Accommodation getByID(int id) throws Exception {
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

    public long getSize() {
        // TODO: implementation
        return 0;
    }

    public void changePricePerPerson(int id, double newPrice)
    {
        // TODO: implementation
    }

    public void changeRating(int id, int newRating)
    {
        // TODO: implementation
    }
}
