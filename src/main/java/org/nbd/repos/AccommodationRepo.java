package org.nbd.repos;

import org.nbd.model.Accommodation;

import java.util.ArrayList;

public class AccommodationRepo {

    private ArrayList<Accommodation> accommodations = new ArrayList<>();

    public boolean add(Accommodation hotel)
    {
        return accommodations.add(hotel);
    }

    public Accommodation getByIndex(int index) throws Exception
    {
        if(index >= accommodations.size() || index < 0)
        {
            throw new Exception("index out of range");
        }
        return accommodations.get(index);
    }

    public int getSize()
    {
        return accommodations.size();
    }
}
