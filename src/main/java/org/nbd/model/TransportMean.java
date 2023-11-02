package org.nbd.model;

import lombok.Getter;

public abstract class TransportMean {
    private boolean isAvailable = true;
    private final int maxWeight;

    @Getter
    private String type;


    public TransportMean(int maxWeight) {
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
