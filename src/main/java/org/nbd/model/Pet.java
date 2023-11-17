package org.nbd.model;

import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
public class Pet {
    @BsonProperty("petName")
    private final String petName;
    @BsonProperty("species")
    private final String species;
    @BsonProperty("petWeight")
    private final int petWeight;

    @BsonCreator
    public Pet(@BsonProperty("petName") String petName, @BsonProperty("species") String species, @BsonProperty("petWeight") int petWeight) {
        this.petName = petName;
        this.species = species;
        this.petWeight = petWeight;
    }

    public String getPetName() {
        return petName;
    }

    public String getSpecies() {
        return species;
    }

    public int getPetWeight() {
        return petWeight;
    }
}
