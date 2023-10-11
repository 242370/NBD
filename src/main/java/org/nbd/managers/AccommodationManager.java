package org.nbd.managers;

import org.nbd.model.Accommodation;
import org.nbd.repos.AccommodationRepo;

import java.util.ArrayList;
import java.util.List;

public class AccommodationManager {
    private AccommodationRepo accommodationRepo = new AccommodationRepo();

    public Accommodation getByIndex(int index)
    {
        Accommodation accommodation = null;
        try {
            accommodation = this.accommodationRepo.getByIndex(index);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return accommodation;
    }
    public boolean addPlace(double capacity, double pricePerPerson, int rating, String destination) throws Exception
    {
        if(rating > 5 || rating < 1)
        {
            throw new Exception("incorrect rating");
        }
        return this.accommodationRepo.add(new Accommodation(capacity, pricePerPerson, rating, destination));
    }
    public List<Accommodation> getAll()
    {
        ArrayList<Accommodation> accommodations = new ArrayList<>();
        for(int i = 0 ; i < this.accommodationRepo.getSize() ; i++)
        {
            try {
                accommodations.add(this.accommodationRepo.getByIndex(i));
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        return accommodations;
    }
}
