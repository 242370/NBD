package org.nbd.dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import org.nbd.model.Accommodation;
import org.nbd.model.Jet;
import org.nbd.model.Lift;
import org.nbd.model.Scooter;

@Dao
public interface TransportDao {
    @Insert
    void createJet(Jet jet);

    @Insert
    void createLIft(Lift lift);

    @Insert
    void createScooter(Scooter scooter);

    @Select
    Jet readJet(String type, int id);

    @Select
    Lift readLift(String type, int id);

    @Select
    Scooter readScooter(String type, int id);

    @Delete
    void deleteJet(Jet jet);

    @Delete
    void deleteLift(Lift lift);

    @Delete
    void deleteScooter(Scooter scooter);

    @Update
    void updateJet(Jet jet);

    @Update
    void updateLift(Lift lift);

    @Update
    void updateScooter(Scooter scooter);
}
