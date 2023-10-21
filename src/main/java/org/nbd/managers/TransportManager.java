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

    public TransportMean getByID(int id)
    {
        return transportRepo.getByID(id);
    }

    public boolean addJet(int maxWeight)
    {
        Jet jet = new Jet(maxWeight);
        return transportRepo.add(jet);
    }

    public boolean addScooter(int maxWeight)
    {
        Scooter jet = new Scooter(maxWeight);
        return transportRepo.add(jet);
    }

    public boolean addLift(int maxWeight)
    {
        Lift jet = new Lift(maxWeight);
        return transportRepo.add(jet);
    }
    public List<TransportMean> getAll()
    {
        ArrayList<TransportMean> accommodations = new ArrayList<>();
        for(int i = 0 ; i < this.transportRepo.getSize() ; i++)
        {
            try {
                accommodations.add(this.transportRepo.getByID(i));
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        return accommodations;
    }
}
