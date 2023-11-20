package org.nbd.repos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import org.nbd.model.*;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class TripRepo extends AbstractMongoRepo implements IRepo<Trip> {
    private final String collectionName = "trips";
    private MongoCollection<TripMgd> trips;
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

        this.trips = this.getDatabase().getCollection(collectionName, TripMgd.class);
    }

    @Override
    public void add(Trip trip){
        trip.getTransportMean().setAvailable(false);

        this.trips.insertOne(TripMapper.toMongoTrip(trip));
    }

    @Override
    public Trip getByID(int id){
        return TripMapper.fromMongoTrip(this.getDatabase().getCollection(collectionName, TripMgd.class).find(eq("id", id))
                .into(new ArrayList<>()).get(0));
    }

    @Override
    public void remove(int id){
        // TODO: implementation
    }

    @Override
    public long getSize() {
        // TODO: implementation
        return 0;
    }

    public void addClientToTrip(Trip trip, Client client) {
        try {
            trip.addClient(client);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
