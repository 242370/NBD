package org.nbd.model;

import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
public class Client {
    @BsonProperty("_id")
    private final int id;
    @BsonProperty("name")
    private String firstName;
    @BsonProperty("lastName")
    private String lastName;
    @BsonProperty("weight")
    private int weight;
    @BsonProperty("hasPet")
    private boolean hasPet = false;
    @BsonProperty("pet")
    private Pet pet;


    public Client(@BsonProperty("_id") int id, @BsonProperty("firstName") String firstName, @BsonProperty("lastName") String lastName,
                  @BsonProperty("weight") int weight) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
    }

    @BsonCreator
    public Client(@BsonProperty("_id") int id, @BsonProperty("firstName") String firstName, @BsonProperty("lastName") String lastName,
                  @BsonProperty("weight") int weight, @BsonProperty("hasPet") boolean hasPet, @BsonProperty("pet") Pet pet) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.hasPet = hasPet;
        this.pet = pet;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getWeight() {
        if (this.hasPet) {
            return this.weight + this.pet.getPetWeight();
        }
        return this.weight;
    }

    public boolean hasPet() {
        return hasPet;
    }

    public Pet getPet() {
        return this.pet;
    }

    public void addPet(String name, String species, int weight) {
        Pet newPet = new Pet(name, species, weight);
        this.hasPet = true;
        this.pet = newPet;
    }
}
