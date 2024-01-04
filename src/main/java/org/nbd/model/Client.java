package org.nbd.model;

import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.entity.naming.GetterStyle;
import com.datastax.oss.driver.api.mapper.entity.naming.NamingConvention;
import com.datastax.oss.driver.api.mapper.entity.naming.SetterStyle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(defaultKeyspace = "trips_DB")
@CqlName("Client")
@PropertyStrategy(mutable = true,
        getterStyle = GetterStyle.JAVABEANS,
        setterStyle = SetterStyle.JAVABEANS)
@NamingStrategy(convention = NamingConvention.EXACT_CASE)
public class Client {
    @PartitionKey
    private int id;
    @ClusteringColumn
    private String lastname;
    private String firstname;

    private int weight;
    private boolean haspet;
    private int tripid;

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isHaspet() {
        return haspet;
    }

    public int getTripid() {
        return tripid;
    }
}
