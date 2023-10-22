package org.nbd.managers;

import org.nbd.model.*;
import org.nbd.repos.TransportRepo;

import java.util.ArrayList;
import java.util.List;

public class TransportManager {
    private TransportRepo transportRepo;

    public TransportManager(TransportRepo transportRepo) {
        this.transportRepo = transportRepo;
    }

    public TransportMean getByID(int id) {
        return transportRepo.getByID(id);
    }

    public void addJet(int maxWeight) {
        Jet jet = new Jet(maxWeight);
        transportRepo.add(jet);
    }

    public void addScooter(int maxWeight) {
        Scooter jet = new Scooter(maxWeight);
        transportRepo.add(jet);
    }

    public void addLift(int maxWeight) {
        Lift jet = new Lift(maxWeight);
        transportRepo.add(jet);
    }

    public void remove(int id) {
        this.transportRepo.remove(id);
    }
}
