package org.nbd.model;

public class Lift extends TransportMean{

    public Lift(int maxWeight) {
        super(maxWeight);
    }

    public boolean isPetSupportive()
    {
        return true;
    }

    public String getType()
    {
        return "Lift";
    }

    @Override
    public void setAvailable(boolean available) {
        super.setAvailable(available);
    }
}
