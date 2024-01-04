package org.nbd.dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import org.nbd.model.Accommodation;

@Dao
public interface AccommodationDao {
    @Insert
    void create(Accommodation accommodation);

    @Select
    Accommodation read(int id);

    @Delete
    void delete(Accommodation accommodation);
}
