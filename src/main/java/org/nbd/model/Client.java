package org.nbd.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;

@Getter
public class Client implements Serializable {
    @BsonProperty("id")
    private final int id;
    @BsonProperty("name")
    private final String firstName;
    @BsonProperty("lastName")
    private final String lastName;
    @BsonProperty("weight")
    private final int weight;
    @BsonProperty("hasPet")
    private boolean hasPet = false;
    @BsonProperty("pet")
    private Pet pet;
    @Setter
    @BsonProperty("trip_id")
    private int trip_id;


    public Client(int id, String firstName, String lastName, int weight) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
    }

    @BsonCreator
    public Client(@BsonProperty("id") int id, @BsonProperty("firstName") String firstName, @BsonProperty("lastName") String lastName,
                  @BsonProperty("weight") int weight, @BsonProperty("hasPet") boolean hasPet, @BsonProperty("pet") Pet pet
            , @BsonProperty("trip_id") int trip_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.hasPet = hasPet;
        this.pet = pet;
        this.trip_id = trip_id;
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

    public String toString() {
        if (hasPet) {
            return "ID: " + getId() + "\n" +
                    "name: " + getFirstName() + "\n" +
                    "last name: " + getLastName() + "\n" +
                    "weight (with pet): " + getWeight() + "\n" +
                    "has pet: " + hasPet() + "\n" +
                    "pet name: " + getPet().getPetName() + "\n" +
                    "pet species: " + getPet().getSpecies() + "\n" +
                    "trip ID: " + getTrip_id() + "\n";
        } else {
            return "ID: " + getId() + "\n" +
                    "name: " + getFirstName() + "\n" +
                    "last name: " + getLastName() + "\n" +
                    "weight: " + getWeight() + "\n" +
                    "has pet: " + hasPet() + "\n" +
                    "trip ID: " + getTrip_id() + "\n";
        }
    }
}
