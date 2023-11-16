package org.nbd.repos;

import com.mongodb.client.MongoIterable;
import org.nbd.model.Accommodation;
import org.nbd.model.Client;
import org.nbd.model.Trip;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class TripRepo extends AbstractMongoRepo implements IRepo<Trip> {
    private final String collectionName = "trips";

    public TripRepo() {
        super.initDbConnection();

        MongoIterable<String> list = this.getDatabase().listCollectionNames();
        for (String name : list) {
            if (name.equals(collectionName)) {
                this.getDatabase().getCollection(name).drop();
                break;
            }
        }

        this.getDatabase().createCollection(collectionName);
    }

    @Override
    public void add(Trip trip) {
        // TODO: implementation
    }

    @Override
    public Trip getByID(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }

        return this.getDatabase().getCollection(collectionName, Trip.class).find(eq("_id", id))
                .into(new ArrayList<>()).get(0);
    }

    @Override
    public void remove(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        // TODO: implementation
    }

    @Override
    public long getSize() {
        // TODO: implementation
        return 0;
    }

    public void addClientToTrip(Trip trip, Client client) {
        // TODO: implementation
    }
}
