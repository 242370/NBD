package org.nbd;

import org.nbd.model.Accommodation;
import org.nbd.repos.AccommodationRepo;
import org.nbd.repos.CassandraManager;

public class ProgramInstance {
    public static void main(String[] args) {
        System.out.println("Welcome to Shark Tours!");

        try(CassandraManager cassandra = new CassandraManager())
        {
            AccommodationRepo repo = new AccommodationRepo(CassandraManager.getSession());
            Accommodation accommodation = new Accommodation(1, "Zgierz", 5.0, 10.0, 4);

            repo.add(accommodation);

            System.out.println("Git");

            Accommodation newa = repo.getByID(1);
            System.out.println(newa.getDestination());
            // TODO: application.conf to resources
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}