package org.nbd.repos;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import org.nbd.dao.DaoMapper;
import org.nbd.dao.DaoMapperBuilder;
import org.nbd.dao.TripDao;
import org.nbd.model.Client;
import org.nbd.model.Trip;

public class TripRepo implements IRepo<Trip> {
    private CqlSession session;
    private TripDao dao;

    public TripRepo(CqlSession session) {
        this.session = session;

        SimpleStatement createTable = SchemaBuilder.createTable(CqlIdentifier.fromCql("Trip")).ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql("id"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("length"), DataTypes.INT)
                .withClusteringColumn(CqlIdentifier.fromCql("name"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("clients"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("transportmean"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("accommodation"), DataTypes.INT).build();
        this.session.execute(createTable);

        DaoMapper mapper = new DaoMapperBuilder(this.session).build();
        this.dao = mapper.getTripDao(CqlIdentifier.fromCql("trips_DB"));
    }

    @Override
    public void add(Trip trip) {
        this.dao.create(trip);
    }

    @Override
    public Trip getByID(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        // TODO: implementation
        return this.dao.read(id);
    }

    @Override
    public void remove(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        this.dao.delete(this.getByID(id));
    }

    @Override
    public long getSize() {
        return 0;
    }

    public void addClientToTrip(Trip trip) {
        trip.setClients(trip.getClients() + 1);
        this.dao.update(trip);
    }

    public void update(Trip trip) {
        this.dao.update(trip);
    }
}
