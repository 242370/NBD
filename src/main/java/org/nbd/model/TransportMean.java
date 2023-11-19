package org.nbd.model;

import lombok.Getter;

@Getter
public abstract class TransportMean {
    private int id;
    private boolean isAvailable = true;
    private final int maxWeight;

    private String type;


    public TransportMean(int id, int maxWeight) {
        this.id = id;
        this.maxWeight = maxWeight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getMaxWeight() {
        return maxWeight;
    }


    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public abstract boolean isPetSupportive();
}
