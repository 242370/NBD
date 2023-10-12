package org.nbd.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class TransportMean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int ID;

    @Column
    private boolean isAvailable = true;

    @Column
    private final int maxWeight;

    @Column(insertable = false)
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

    public int getID() {
        return ID;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public abstract boolean isPetSupportive();

    public abstract String getType();
}
