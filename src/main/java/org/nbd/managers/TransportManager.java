package org.nbd.managers;

import org.nbd.model.*;
import org.nbd.repos.TransportRepo;

public class TransportManager {
    private TransportRepo transportRepo;

    public TransportManager(TransportRepo transportRepo) {
        this.transportRepo = transportRepo;
    }

    public TransportMean getByID(int id) {
        TransportMean transportMean = null;
        try {
            transportMean = transportRepo.getByID(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return transportMean;
    }

    public void addJet(int id, int maxWeight) {
        Jet jet = new Jet(id, maxWeight);
        transportRepo.add(jet);
    }

    public void addScooter(int id, int maxWeight) {
        Scooter jet = new Scooter(id, maxWeight);
        transportRepo.add(jet);
    }

    public void addLift(int id, int maxWeight) {
        Lift jet = new Lift(id, maxWeight);
        transportRepo.add(jet);
    }

    public void remove(int id) {
        try {
            this.transportRepo.remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}