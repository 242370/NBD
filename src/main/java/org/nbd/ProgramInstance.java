package org.nbd;

import org.nbd.model.Accommodation;
import org.nbd.model.Client;
import org.nbd.repos.AccommodationRepo;
import org.nbd.repos.CassandraManager;
import org.nbd.repos.ClientRepo;

public class ProgramInstance {
    public static void main(String[] args) {
        System.out.println("Welcome to Shark Tours!");

        try(CassandraManager cassandra = new CassandraManager())
        {

            // Accommodation test
            AccommodationRepo repo = new AccommodationRepo(CassandraManager.getSession());
            Accommodation accommodation = new Accommodation(1, "Zgierz", 5.0, 10.0, 4);

            repo.add(accommodation);

            System.out.println("Git");

            Accommodation newa = repo.getByID(1);
            System.out.println(newa.getDestination());
            // TODO: application.conf to resources

            repo.remove(1);
            newa = repo.getByID(1);
            // System.out.println(newa.getDestination());

            // Client test
            System.out.println();
            System.out.println("CLIENT TESTING");
            ClientRepo crepo = new ClientRepo(CassandraManager.getSession());
            Client client = new Client(1, "Rafal", "Cyberbully", 55, true, 1);

            crepo.add(client);
            System.out.println("Client git");

            Client newc = crepo.getByID(1);
            System.out.println(newc.getLastname());

            crepo.remove(1);
            newc = crepo.getByID(1);
            System.out.println(newc.getLastname());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}