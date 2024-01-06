package org.nbd.model;

import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.entity.naming.GetterStyle;
import com.datastax.oss.driver.api.mapper.entity.naming.NamingConvention;
import com.datastax.oss.driver.api.mapper.entity.naming.SetterStyle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(defaultKeyspace = "trips_DB")
@CqlName("Transport")
@HierarchyScanStrategy(scanAncestors = true,
        highestAncestor = TransportMean.class,
        includeHighestAncestor = true)
@PropertyStrategy(mutable = true,
        getterStyle = GetterStyle.JAVABEANS,
        setterStyle = SetterStyle.JAVABEANS)
@NamingStrategy(convention = NamingConvention.EXACT_CASE)

@NoArgsConstructor
@Getter
@Setter
public class Lift extends TransportMean{
    private int capacity;

    public Lift(String type, int id, boolean isavailable, int maxweight, int capacity)
    {
        super(type, id, isavailable, maxweight);
        this.capacity = capacity;
    }
}
