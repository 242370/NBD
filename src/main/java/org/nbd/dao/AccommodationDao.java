package org.nbd.dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import org.nbd.model.Accommodation;

@Dao
public interface AccommodationDao {
    @Insert
    @StatementAttributes(consistencyLevel = "ALL")
    void create(Accommodation accommodation);

    @Select
    @StatementAttributes(consistencyLevel = "ONE")
    Accommodation read(int id);

    @Delete
    @StatementAttributes(consistencyLevel = "ALL")
    void delete(Accommodation accommodation);
}
