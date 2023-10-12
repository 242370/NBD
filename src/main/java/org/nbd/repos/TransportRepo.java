package org.nbd.repos;

import jakarta.persistence.EntityManager;
import org.nbd.model.TransportMean;

import java.util.ArrayList;

public class TransportRepo {
    private ArrayList<TransportMean> transportMeans = new ArrayList<>();

    EntityManager entityManager;

    public boolean add(TransportMean transportMean)
    {
        return transportMeans.add(transportMean);
    }
    public TransportMean getByIndex(int index) throws Exception
    {
        if(index >= transportMeans.size() || index < 0)
        {
            throw new Exception("index out of range");
        }
        return transportMeans.get(index);
    }
    public TransportMean getById(int ID) throws Exception
    {
        for (TransportMean transportMean : transportMeans)
        {
            if(ID == transportMean.getID())
            {
                return transportMean;
            }
        }
        throw new Exception("invalid ID");
    }
    public int getSize()
    {
        return transportMeans.size();
    }
}
