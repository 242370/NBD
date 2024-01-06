package org.nbd.dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import org.nbd.model.Client;

@Dao
public interface ClientDao {
    @Insert
    @StatementAttributes(consistencyLevel = "ALL")
    void create(Client client);

    @Select
    @StatementAttributes(consistencyLevel = "ONE")
    Client read(int id);

    @Update
    @StatementAttributes(consistencyLevel = "ALL")
    void update(Client client);

    @Delete
    @StatementAttributes(consistencyLevel = "ALL")
    void delete(Client client);
}
