package org.nbd.model;

import java.util.ArrayList;

public class Client {
    private String firstName;
    private String lastName;
    private int weight;
    private boolean hasPet = false;
    private ArrayList<Pet> pets;

    public Client(String firstName, String lastName, int weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isHasPet() {
        return hasPet;
    }

    public Pet getPet(int index) {
        return pets.get(index);
    }

    public boolean addPet(String name, String species, int weight) {
        Pet newPet = new Pet(name, species, weight);
        this.hasPet = true;
        return pets.add(newPet);
    }
}
