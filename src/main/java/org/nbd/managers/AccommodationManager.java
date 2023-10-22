package org.nbd.managers;

import org.nbd.model.Accommodation;
import org.nbd.repos.AccommodationRepo;

public class AccommodationManager {
    private AccommodationRepo accommodationRepo;

    public AccommodationManager(AccommodationRepo accommodationRepo) {
        this.accommodationRepo = accommodationRepo;
    }

    public Accommodation getByID(int id)
    {
        return accommodationRepo.getByID(id);
    }
    public void addPlace(double capacity, double pricePerPerson, int rating, String destination) throws Exception
    {
        if(rating > 5 || rating < 1)
        {
            throw new Exception("incorrect rating");
        }
        Accommodation accommodation = new Accommodation(capacity, pricePerPerson, rating, destination);
        this.accommodationRepo.add(accommodation);
    }

    public void changePricePerPerson(int id, double newPrice)
    {
        this.accommodationRepo.changePricePerPerson(id, newPrice);
    }

    public void changeRating(int id, int newRating)
    {
        this.accommodationRepo.changeRating(id, newRating);
    }

    public void remove(int id)
    {
        this.accommodationRepo.remove(id);
    }
}
