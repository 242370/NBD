package org.nbd.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private final String firstName;
    @Column(name = "last_name")
    private final String lastName;
    @Column
    private final int weight;

    @Column(name = "has_pet")
    private boolean hasPet = false;

    @Embedded
    private Pet pet = null;

    public Client(String firstName, String lastName, int weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
    }

    public int getWeight() {
        if(this.hasPet)
        {
            return this.weight + this.pet.getPetWeight();
        }
        return this.weight;
    }

    public boolean hasPet() {
        return hasPet;
    }

    public void addPet(String name, String species, int weight){
        Pet newPet = new Pet(name, species, weight);
        this.hasPet = true;
        this.pet = newPet;
    }
}
