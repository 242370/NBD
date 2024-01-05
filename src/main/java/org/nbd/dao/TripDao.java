package org.nbd.dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import org.nbd.model.Trip;

@Dao
public interface TripDao {
    @Insert
    void create(Trip trip);

    @Select
    Trip read(int id);

    @Update
    void update(Trip trip);

    @Delete
    void delete(Trip trip);
}
