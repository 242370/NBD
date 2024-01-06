package org.nbd.repos;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import org.nbd.dao.DaoMapper;
import org.nbd.dao.DaoMapperBuilder;
import org.nbd.dao.TransportDao;
import org.nbd.model.Jet;
import org.nbd.model.Lift;
import org.nbd.model.Scooter;
import org.nbd.model.TransportMean;

public class TransportRepo {
    private CqlSession session;
    private TransportDao dao;

    public TransportRepo(CqlSession session) {
        this.session = session;

        SimpleStatement createTable = SchemaBuilder.createTable(CqlIdentifier.fromCql("Transport")).ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql("type"), DataTypes.TEXT)
                .withClusteringColumn(CqlIdentifier.fromCql("id"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("isavailable"), DataTypes.BOOLEAN)
                .withColumn(CqlIdentifier.fromCql("maxweight"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("velocity"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("capacity"), DataTypes.INT).build();
        this.session.execute(createTable);

        DaoMapper mapper = new DaoMapperBuilder(this.session).build();
        this.dao = mapper.getTransportDao(CqlIdentifier.fromCql("trips_DB"));
    }

    public void add(TransportMean transportMean) {
        if (transportMean.getClass().getSimpleName().equals("Jet")) {
            this.dao.createJet((Jet) transportMean);
        } else if (transportMean.getClass().getSimpleName().equals("Lift")) {
            this.dao.createLIft((Lift) transportMean);
        } else {
            this.dao.createScooter((Scooter) transportMean);
        }
    }

    public TransportMean getBy(int id, String type) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        return switch (type) {
            case "Jet" -> this.dao.readJet(Jet.class.getSimpleName(), id);
            case "Lift" -> this.dao.readLift(Lift.class.getSimpleName(), id);
            case "Scooter" -> this.dao.readScooter(Lift.class.getSimpleName(), id);
            default -> null;
        };
    }

    public void remove(int id, String type) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        if (type == "Jet" )
        {
            this.dao.deleteJet((Jet) this.getBy(id, type));
        } else if (type == "Lift") {
            this.dao.deleteLift((Lift) this.getBy(id, type));
        }
        else
        {
            this.dao.deleteScooter((Scooter) this.getBy(id, type));
        }
    }
    public long getSize() {
        return 0;
    }

    public void changeAvailability(TransportMean transportMean)
    {
        transportMean.setIsavailable(!transportMean.isIsavailable());
        if (transportMean.getClass().getSimpleName().equals("Jet")) {
            this.dao.updateJet((Jet) transportMean);
        } else if (transportMean.getClass().getSimpleName().equals("Lift")) {
            this.dao.updateLift((Lift) transportMean);
        } else {
            this.dao.updateScooter((Scooter) transportMean);
        }
    }
}
