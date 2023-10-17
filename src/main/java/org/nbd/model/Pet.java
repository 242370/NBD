package org.nbd.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor
public class Pet {

    @Column(name = "pet_name")
    private String petName = null;

    private String species = null;

    @Column(name = "pet_weight")
    private int petWeight = 0;

    public Pet(String petName, String species, int petWeight) {
        this.petName = petName;
        this.species = species;
        this.petWeight = petWeight;
    }
    public int getPetWeight() {
        return petWeight;
    }
}
