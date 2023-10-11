package org.nbd.model;

public class Pet {
    private final String name;
    private final String species;
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
