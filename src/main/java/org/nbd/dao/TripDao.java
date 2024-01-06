package org.nbd.dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import org.nbd.model.Trip;

@Dao
public interface TripDao {
    @Insert
    @StatementAttributes(consistencyLevel = "ALL")
    void create(Trip trip);

    @Select
    @StatementAttributes(consistencyLevel = "ONE")
    Trip read(int id);

    @Update
    @StatementAttributes(consistencyLevel = "ALL")
    void update(Trip trip);

    @Delete
    @StatementAttributes(consistencyLevel = "ALL")
    void delete(Trip trip);
}
