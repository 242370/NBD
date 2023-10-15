package org.nbd.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;


public class Client {

    private final String firstName;

    private final String lastName;

    private final int weight;

    private boolean hasPet = false;

    private List<Pet> pets = new ArrayList<>();

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

    public boolean hasPet() {
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
