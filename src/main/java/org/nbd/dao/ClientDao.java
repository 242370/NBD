package org.nbd.dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import org.nbd.model.Client;

@Dao
public interface ClientDao {
    @Insert
    void create(Client client);

    @Select
    Client read(int id);

    @Update
    void update(Client client);

    @Delete
    void delete(Client client);
}
