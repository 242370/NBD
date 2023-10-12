package org.nbd.model;

public class Scooter extends TransportMean{
    public Scooter(int maxWeight) {
        super(maxWeight);
    }

    public boolean isPetSupportive()
    {
        return true;
    }

    public String getType()
    {
        return "Scooter";
    }

    @Override
    public void setAvailable(boolean available) {
        super.setAvailable(available);
    }
}
