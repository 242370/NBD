package org.nbd.managers;

import org.nbd.model.Accommodation;
import org.nbd.repos.AccommodationRepo;

public class AccommodationManager {
    private AccommodationRepo accommodationRepo;

    public AccommodationManager(AccommodationRepo accommodationRepo) {
        this.accommodationRepo = accommodationRepo;
    }

    public Accommodation getByID(int id) {
        Accommodation accommodation = null;
        try {
            accommodation = accommodationRepo.getByID(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return accommodation;
    }

//    public void addPlace(double capacity, double pricePerPerson, int rating, String destination) throws Exception {
//        if (rating > 5 || rating < 1) {
//            throw new Exception("Incorrect rating");
//        }
//        Accommodation accommodation = new Accommodation(capacity, pricePerPerson, rating, destination);
//        this.accommodationRepo.add(accommodation);
//    }

    public void changePricePerPerson(int id, double newPrice) {
        this.accommodationRepo.changePricePerPerson(id, newPrice);
    }

    public void changeRating(int id, int newRating) {
        this.accommodationRepo.changeRating(id, newRating);
    }

    public void remove(int id) {
        try {
            this.accommodationRepo.remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}