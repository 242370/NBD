package org.nbd.model;

import jakarta.persistence.Entity;

@Entity
public class Scooter extends TransportMean {
    public Scooter(int maxWeight) {
        super(maxWeight);
    }

    public boolean isPetSupportive() {
        return false;
    }

    public String getType() {
        return "Scooter";
    }

    @Override
    public void setAvailable(boolean available) {
        super.setAvailable(available);
    }
}
