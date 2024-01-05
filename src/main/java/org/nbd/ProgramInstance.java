package org.nbd;

import org.nbd.model.Accommodation;
import org.nbd.model.Client;
import org.nbd.repos.AccommodationRepo;
import org.nbd.repos.CassandraManager;
import org.nbd.repos.ClientRepo;

public class ProgramInstance {
    public static void main(String[] args) {
        System.out.println("Welcome to Shark Tours!");

        try (CassandraManager cassandra = new CassandraManager()) {
            // TODO: application.conf to resources
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}