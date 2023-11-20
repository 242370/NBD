package org.nbd.model;

import lombok.Getter;

@Getter
public abstract class TransportMean {
    private int id;
    private int uses = 0;
    private final int maxWeight;

    private String type;


    public TransportMean(int id, int maxWeight) {
        this.id = id;
        this.maxWeight = maxWeight;
    }


    public int getMaxWeight() {
        return maxWeight;
    }

    public boolean isAvailable()
    {
        if(uses == 0)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public void setAvailable(boolean available) {
        if(available)
        {
            uses = 0;
        }
        else
        {
            uses++;
        }
    }

    public abstract boolean isPetSupportive();
}
