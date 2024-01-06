package org.nbd.dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import org.nbd.model.Accommodation;
import org.nbd.model.Jet;
import org.nbd.model.Lift;
import org.nbd.model.Scooter;

@Dao
public interface TransportDao {
    @Insert
    @StatementAttributes(consistencyLevel = "ALL")
    void createJet(Jet jet);

    @Insert
    @StatementAttributes(consistencyLevel = "ALL")
    void createLIft(Lift lift);

    @Insert
    @StatementAttributes(consistencyLevel = "ALL")
    void createScooter(Scooter scooter);

    @Select
    @StatementAttributes(consistencyLevel = "ONE")
    Jet readJet(String type, int id);

    @Select
    @StatementAttributes(consistencyLevel = "ONE")
    Lift readLift(String type, int id);

    @Select
    @StatementAttributes(consistencyLevel = "ONE")
    Scooter readScooter(String type, int id);

    @Delete
    @StatementAttributes(consistencyLevel = "ALL")
    void deleteJet(Jet jet);

    @Delete
    @StatementAttributes(consistencyLevel = "ALL")
    void deleteLift(Lift lift);

    @Delete
    @StatementAttributes(consistencyLevel = "ALL")
    void deleteScooter(Scooter scooter);

    @Update
    @StatementAttributes(consistencyLevel = "ALL")
    void updateJet(Jet jet);

    @Update
    @StatementAttributes(consistencyLevel = "ALL")
    void updateLift(Lift lift);

    @Update
    @StatementAttributes(consistencyLevel = "ALL")
    void updateScooter(Scooter scooter);
}
