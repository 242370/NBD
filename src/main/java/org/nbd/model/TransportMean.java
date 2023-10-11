package org.nbd.model;

public abstract class TransportMean {
    private boolean isAvailable = true;
    private final int maxWeight;
    private final int ID;


    public TransportMean(int maxWeight, int ID) {
        this.maxWeight = maxWeight;
        this.ID = ID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getID() {
        return ID;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public abstract boolean isPetSupportive();
}
