package org.nbd.repos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import org.nbd.model.Accommodation;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class AccommodationRepo extends AbstractMongoRepo implements IRepo<Accommodation> {
    private final String collectionName = "accommodations";

    public AccommodationRepo() {
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

    public void add(Accommodation hotel) {
        MongoCollection<Accommodation> accommodations = this.getDatabase().getCollection(collectionName, Accommodation.class);

        accommodations.insertOne(hotel);
    }

    @Override
    public Accommodation getByID(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }

        return this.getDatabase().getCollection(collectionName, Accommodation.class).find(eq("_id", id))
                .into(new ArrayList<>()).get(0);
    }

    @Override
    public void remove(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Id cannot be below 1");
        }
        // TODO: implementation
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
