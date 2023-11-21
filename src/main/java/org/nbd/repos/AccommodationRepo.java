package org.nbd.repos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
import org.nbd.model.Accommodation;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class AccommodationRepo extends AbstractMongoRepo implements IRepo<Accommodation> {
    private final String collectionName = "accommodations";
    private final MongoCollection<Accommodation> accommodations;

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

        this.accommodations = this.getDatabase().getCollection(collectionName, Accommodation.class);
    }

    public void add(Accommodation hotel) {
        this.accommodations.insertOne(hotel);
    }

    @Override
    public Accommodation getByID(int id) {
        return this.accommodations.find(eq("id", id))
                .into(new ArrayList<>()).get(0);
    }

    @Override
    public void remove(int id){
        Bson filter = Filters.eq("id", id);

        this.accommodations.findOneAndDelete(filter);
    }

    public long getSize() {
        return accommodations.countDocuments();
    }

    public void changePricePerPerson(int id, double newPrice) {
        Bson filter = Filters.eq("id", id);
        Bson setUpdate = Updates.set("pricePerPerson", newPrice);

        this.accommodations.updateOne(filter, setUpdate);
    }

    public void changeRating(int id, int newRating) {
        Bson filter = Filters.eq("id", id);
        Bson setUpdate = Updates.set("rating", newRating);

        this.accommodations.updateOne(filter, setUpdate);
    }
}
