package org.nbd.repos;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import org.nbd.dao.DaoMapper;
import org.nbd.dao.DaoMapperBuilder;
import org.nbd.dao.TripDao;
import org.nbd.model.Client;
import org.nbd.model.Trip;

import java.util.ArrayList;
import java.util.List;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

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

        SimpleStatement tripsByAccommodation = SchemaBuilder.createTable(CqlIdentifier.fromCql("TripsByAccommodation")).ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql("accommodation"), DataTypes.INT)
                .withClusteringColumn(CqlIdentifier.fromCql("id"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("length"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("name"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("clients"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("transportmean"), DataTypes.INT).build();
        this.session.execute(tripsByAccommodation);

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

    public void addBy(Trip trip) {
        SimpleStatement insert = QueryBuilder.insertInto("TripsByAccommodation")
                .value("accommodation", literal(trip.getAccommodation()))
                .value("id", literal(trip.getId()))
                .value("length", literal(trip.getLength()))
                .value("name", literal(trip.getName()))
                .value("clients", literal(trip.getClients()))
                .value("transportmean", literal(trip.getTransportmean())).build();
        this.session.execute(insert);
    }

    public List<Trip> selectBy(int accommodation) {
        SimpleStatement select = QueryBuilder.selectFrom("TripsByAccommodation").all().
                where(Relation.column("accommodation").isEqualTo(literal(accommodation))).build();

        ResultSet set = this.session.execute(select);
        List<Row> rows = this.session.execute(select).all();

        ArrayList<Trip> trips = new ArrayList<>();
        for (Row row : rows) {
            Trip trip = new Trip(row.get("id", int.class),
                    row.get("length", int.class),
                    row.get("name", String.class),
                    row.get("clients", int.class),
                    row.get("transportmean", int.class),
                    row.get("accommodation", int.class));
            trips.add(trip);
        }
        return trips;
    }

    public void deleteBy(int accommodation)
    {
        SimpleStatement delete = QueryBuilder.deleteFrom("TripsByAccommodation").where(Relation.column("accommodation")
                .isEqualTo(literal(accommodation))).build();
        this.session.execute(delete);
    }
}
