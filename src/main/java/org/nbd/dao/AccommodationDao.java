package org.nbd.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import org.nbd.model.Accommodation;

@Dao
public interface AccommodationDao {
    @Insert
    void create(Accommodation accommodation);

    @Select
    Accommodation readAccommodation(int id);
}
