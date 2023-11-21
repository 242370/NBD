package org.nbd.repos;

import com.mongodb.client.ClientSession;
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
    public void add(Trip trip) {
        MongoCollection<TransportMgd> transportCollection = this.getDatabase().getCollection("transportMeans", TransportMgd.class);
        ClientSession session = getClient().startSession();

        Bson filter = Filters.eq("id", trip.getTransportMean().getId());
        Bson update = Updates.inc("uses", 1);

        if(transportCollection.find(filter).into(new ArrayList<>()).isEmpty())
        {
            transportCollection.insertOne(TransportMapper.toMongoTransport(trip.getTransportMean()));
        }

        try {
            session.startTransaction();

            this.getDatabase().getCollection("transportMeans").updateOne(filter, update);
            this.trips.insertOne(TripMapper.toMongoTrip(trip));

            session.commitTransaction();
            session.close();
        } catch (Exception e) {
            System.out.println("Wanted transport mean is unavailable");
            session.abortTransaction();
            session.close();
        }
        // trip.getTransportMean().setAvailable(false);

    }

    @Override
    public Trip getByID(int id) {
        return TripMapper.fromMongoTrip(this.getDatabase().getCollection(collectionName, TripMgd.class).find(eq("id", id))
                .into(new ArrayList<>()).get(0));
    }

    @Override
    public void remove(int id) {
        ClientSession session = getClient().startSession();
        try {
            session.startTransaction();

            Bson tripFilter = Filters.eq("id", id);

            Bson transportFilter = Filters.eq("id", this.getByID(id).getTransportMean().getId());
            Bson update = Updates.inc("uses" , -1);
            this.getDatabase().getCollection("transportMeans").updateOne(transportFilter, update);

            this.trips.findOneAndDelete(tripFilter);

            session.commitTransaction();
            session.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            session.abortTransaction();
            session.close();
        }
    }

    @Override
    public long getSize() {
        return this.trips.countDocuments();
    }

    public void addClientToTrip(Trip trip, Client client) {
        ClientSession session = getClient().startSession();
        try {
            session.startTransaction();

            trip.addClient(client);
            client.setTrip_id(trip.getId());
            Bson filter = Filters.eq("id", trip.getId());
            Bson setClientsUpdate = Updates.set("clients", trip.getClients());
            Bson setWeightUpdate = Updates.set("actualWeight", trip.getActualWeight());

            this.trips.updateOne(filter, setClientsUpdate);
            this.trips.updateOne(filter, setWeightUpdate);

            session.commitTransaction();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.abortTransaction();
            session.close();
        }
    }
}
