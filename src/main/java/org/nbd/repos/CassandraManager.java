package org.nbd.repos;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;
import lombok.Getter;

import java.net.InetSocketAddress;

public class CassandraManager implements AutoCloseable{
    private static CqlSession session;

    public CassandraManager() {
        this.initSession();
    }

    public void initSession() {
        session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("cassandra1", 9042))
                .addContactPoint(new InetSocketAddress("cassandra2", 9043))
                .addContactPoint(new InetSocketAddress("cassandra3", 9044))
                .withLocalDatacenter("dc1")
                .withAuthCredentials("nbd",
                        "nbdpassword")
                 .withKeyspace("trips_DB")
                .build();

        SimpleStatement keyspace = SchemaBuilder.createKeyspace(CqlIdentifier.fromCql("trips_DB")).ifNotExists().withSimpleStrategy(3)
                .withDurableWrites(true).build();
        session.execute(keyspace);
    }

    public static CqlSession getSession()
    {
        return session;
    }

    @Override
    public void close() throws Exception {

    }
}
