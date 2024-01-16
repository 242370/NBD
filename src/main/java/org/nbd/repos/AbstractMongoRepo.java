package org.nbd.repos;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

@Getter
public abstract class AbstractMongoRepo implements AutoCloseable {
    private static final ConnectionString connectionString = new ConnectionString(
            "mongodb://localhost:27017,localhost:27018,localhost:27019/?replicaSet=replica_set_single");
    private final MongoCredential credential = MongoCredential.createCredential(
            "admin", "admin", "password".toCharArray());

    private final CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder()
            .automatic(true)
            .conventions(List.of(Conventions.ANNOTATION_CONVENTION))
            .build());
    private MongoClient client;
    private MongoDatabase database;

    protected void initDbConnection() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyConnectionString(connectionString)
                .codecRegistry(CodecRegistries.fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        pojoCodecRegistry
                ))
                .build();

        client = MongoClients.create(settings);
        database = client.getDatabase("mongo");
    }

    @Override
    public void close() throws Exception {
        this.client.close();
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
