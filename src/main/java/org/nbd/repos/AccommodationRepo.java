package org.nbd.repos;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import org.nbd.dao.AccommodationDao;
import org.nbd.dao.DaoMapper;
import org.nbd.dao.DaoMapperBuilder;
import org.nbd.model.Accommodation;

public class AccommodationRepo implements IRepo<Accommodation> {
    private CqlSession session;
    private AccommodationDao dao;

    public AccommodationRepo(CqlSession session) {
        this.session  = session;

        SimpleStatement createTable = SchemaBuilder.createTable(CqlIdentifier.fromCql("Accommodation")).ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql("id"), DataTypes.INT)
                .withClusteringColumn(CqlIdentifier.fromCql("destination"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("capacity"), DataTypes.DOUBLE)
                .withColumn(CqlIdentifier.fromCql("price_per_person"), DataTypes.DOUBLE)
                .withColumn(CqlIdentifier.fromCql("rating"), DataTypes.INT).build();
        this.session.execute(createTable);

        DaoMapper mapper = new DaoMapperBuilder(this.session).build();
        this.dao = mapper.getAccommodationDao(CqlIdentifier.fromCql("trips_DB"));
    }

    public void add(Accommodation hotel) {
        this.dao.create(hotel);
    }

    @Override
    public Accommodation getByID(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        return this.dao.read(id);
    }

    @Override
    public void remove(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        this.dao.delete(this.getByID(id));
    }

    public long getSize() {
        // TODO: implementation
        return 0;
    }

    public void changePricePerPerson(int id, double newPrice)
    {
        // TODO: implementation
    }

    public void changeRating(int id, int newRating)
    {
        // TODO: implementation
    }
}
