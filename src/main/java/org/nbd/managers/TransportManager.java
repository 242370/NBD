package org.nbd.managers;

import org.nbd.model.*;
import org.nbd.repos.TransportRepo;

import java.util.ArrayList;
import java.util.List;

public class TransportManager {
    private final TransportRepo transportRepo = new TransportRepo();

    public TransportMean getByIndex(int index)
    {
        TransportMean accommodation = null;
        try {
            accommodation = this.transportRepo.getByIndex(index);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return accommodation;
    }

    public boolean addJet(int maxWeight, int ID)
    {
        Jet jet = new Jet(maxWeight, ID);
        return transportRepo.add(jet);
    }

    public boolean addScooter(int maxWeight, int ID)
    {
        Scooter jet = new Scooter(maxWeight, ID);
        return transportRepo.add(jet);
    }

    public boolean addLift(int maxWeight, int ID)
    {
        Lift jet = new Lift(maxWeight, ID);
        return transportRepo.add(jet);
    }
    public List<TransportMean> getAll()
    {
        ArrayList<TransportMean> accommodations = new ArrayList<>();
        for(int i = 0 ; i < this.transportRepo.getSize() ; i++)
        {
            try {
                accommodations.add(this.transportRepo.getByIndex(i));
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        return accommodations;
    }
}
