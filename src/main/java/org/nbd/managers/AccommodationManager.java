package org.nbd.managers;

import org.nbd.model.Accommodation;
import org.nbd.repos.AccommodationRepo;

import java.util.ArrayList;
import java.util.List;

public class AccommodationManager {
    private AccommodationRepo accommodationRepo;

    public AccommodationManager(AccommodationRepo accommodationRepo) {
        this.accommodationRepo = accommodationRepo;
    }

    public Accommodation getByID(int id)
    {
        return accommodationRepo.getByID(id);
    }
    public boolean addPlace(double capacity, double pricePerPerson, int rating, String destination) throws Exception
    {
        if(rating > 5 || rating < 1)
        {
            throw new Exception("incorrect rating");
        }
        Accommodation accommodation = new Accommodation(capacity, pricePerPerson, rating, destination);
        return this.accommodationRepo.add(accommodation);
    }
    public List<Accommodation> getAll()
    {
        ArrayList<Accommodation> accommodations = new ArrayList<>();
        for(int i = 0 ; i < this.accommodationRepo.getSize() ; i++)
        {
            try {
                accommodations.add(this.accommodationRepo.getByID(i));
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        return accommodations;
    }
}
