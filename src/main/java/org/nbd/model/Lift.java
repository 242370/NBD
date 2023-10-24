package org.nbd.model;

import jakarta.persistence.Entity;

@Entity
public class Lift extends TransportMean {

    public Lift(int maxWeight) {
        super(maxWeight);
    }

    public boolean isPetSupportive() {
        return false;
    }

    public String getType() {
        return "Lift";
    }

    @Override
    public void setAvailable(boolean available) {
        super.setAvailable(available);
    }
}
