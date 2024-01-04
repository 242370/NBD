package org.nbd.repos;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import org.nbd.dao.ClientDao;
import org.nbd.dao.DaoMapper;
import org.nbd.dao.DaoMapperBuilder;
import org.nbd.model.Client;

public class ClientRepo implements IRepo<Client> {
    private CqlSession session;
    private ClientDao dao;

    public ClientRepo(CqlSession session) {
        this.session = session;

        SimpleStatement createTable = SchemaBuilder.createTable(CqlIdentifier.fromCql("Client")).ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql("id"), DataTypes.INT)
                .withClusteringColumn(CqlIdentifier.fromCql("lastname"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("firstname"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("weight"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("haspet"), DataTypes.BOOLEAN)
                .withColumn(CqlIdentifier.fromCql("tripid"), DataTypes.INT).build();
        this.session.execute(createTable);

        DaoMapper mapper = new DaoMapperBuilder(this.session).build();
        this.dao = mapper.getClientDao(CqlIdentifier.fromCql("trips_DB"));
    }

    public void add(Client client) {
        this.dao.create(client);
    }

    public Client getByID(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        return this.dao.read(id);
    }

    public void remove(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        this.dao.delete(this.dao.read(id));
    }

    @Override
    public long getSize() {
        return 0;
    }

    public void update(Client client)
    {
        this.dao.update(client);
    }
}