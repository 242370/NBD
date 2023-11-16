package org.nbd.repos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.nbd.model.Accommodation;
import org.nbd.model.Client;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class ClientRepo extends AbstractMongoRepo implements IRepo<Client> {
    private final String collectionName = "clients";
    private MongoCollection<Client> clients;
    public ClientRepo() {
        super.initDbConnection();

        MongoIterable<String> list = this.getDatabase().listCollectionNames();
        for (String name : list) {
            if (name.equals(collectionName)) {
                this.getDatabase().getCollection(name).drop();
                break;
            }
        }

        this.getDatabase().createCollection(collectionName);

        this.clients = this.getDatabase().getCollection(collectionName, Client.class);
    }

    public void add(Client client) {
        this.clients.insertOne(client);
    }

    public Client getByID(int id) throws Exception{
        if(id < 1)
        {
            throw new Exception("Id cannot be below 1");
        }
        return this.clients.find(eq("_id", id))
                .into(new ArrayList<>()).get(0);
    }

    public void remove(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        Bson filter = Filters.eq("_id", id);

        this.clients.findOneAndDelete(filter);
    }

    public long getSize() {
        // TODO: implementation
        return 0;
    }
}