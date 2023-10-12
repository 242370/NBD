package org.nbd.model;

import jakarta.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    @Column
    private final String name;
    @Column
    private final String species;
    @Column
    private final int weight;

    public Pet(String name, String species, int weight) {
        this.name = name;
        this.species = species;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getWeight() {
        return weight;
    }
}
