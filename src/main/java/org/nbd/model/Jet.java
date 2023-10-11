package org.nbd.model;

public class Jet extends TransportMean{
    public Jet(int maxWeight, int ID) {
        super(maxWeight, ID);
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
