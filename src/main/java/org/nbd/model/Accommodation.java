package org.nbd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Accommodations")
@NoArgsConstructor
@Getter
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private double capacity;
    @Column
    @Setter
    private double pricePerPerson;
    @Column
    @Setter
    private int rating;
    @Column
    private String destination;

    public Accommodation(double capacity, double pricePerPerson, int rating, String destination) {
        this.capacity = capacity;
        this.pricePerPerson = pricePerPerson;
        this.rating = rating;
        this.destination = destination;
    }

}
