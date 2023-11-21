package org.nbd.repos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
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
        Bson filter = Filters.eq("id", id);

        this.trips.findOneAndDelete(filter);
    }

    @Override
    public long getSize() {
        return this.trips.countDocuments();
    }

    public void addClientToTrip(Trip trip, Client client) {
        try {
            trip.addClient(client);
            client.setTrip_id(trip.getId());
            Bson filter = Filters.eq("id", trip.getId());
            Bson setClientsUpdate = Updates.set("clients", trip.getClients());
            Bson setWeightUpdate = Updates.set("actualWeight", trip.getActualWeight());

            this.trips.updateOne(filter, setClientsUpdate);
            this.trips.updateOne(filter, setWeightUpdate);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
