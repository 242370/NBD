package org.nbd.model;

public class Jet extends TransportMean{
    public Jet(int maxWeight) {
        super(maxWeight);
    }

    public boolean isPetSupportive()
    {
        return true;
    }

    public String getType()
    {
        return "Jet";
    }

    @Override
    public void setAvailable(boolean available) {
        super.setAvailable(available);
    }
}
