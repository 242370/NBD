package org.nbd.repos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.nbd.model.Client;
import org.nbd.model.TransportMapper;
import org.nbd.model.TransportMean;
import org.nbd.model.TransportMgd;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class TransportRepo extends AbstractMongoRepo implements IRepo<TransportMean> {
    private final String collectionName = "transportMeans";
    private MongoCollection<TransportMgd> transportMeans;

    public TransportRepo() {
        super.initDbConnection();

        MongoIterable<String> list = this.getDatabase().listCollectionNames();
        for (String name : list) {
            if (name.equals(collectionName)) {
                this.getDatabase().getCollection(name).drop();
                break;
            }
        }

        ValidationOptions validationOptions = new ValidationOptions().validator(
                Document.parse("""
                        {
                            '$jsonSchema': {
                              "bsonType": "object",
                               "required": [ "uses" ],
                               "properties": {
                                   "uses": {
                                      "bsonType": "int",
                                      "minimum": 0,
                                      "maximum": 1
                                    }
                                }
                            }
                        }
                        """)
        );

        CreateCollectionOptions collectionOptions = new CreateCollectionOptions().validationOptions(validationOptions);

        this.getDatabase().createCollection(collectionName, collectionOptions);

        this.transportMeans = this.getDatabase().getCollection(collectionName, TransportMgd.class);
    }

    public void add(TransportMean transportMean) {
        transportMeans.insertOne(TransportMapper.toMongoTransport(transportMean));
    }

    @Override
    public TransportMean getByID(int id) {
        return TransportMapper.fromMongoTransport(this.transportMeans.find(eq("id", id))
                .into(new ArrayList<>()).get(0));
    }

    @Override
    public void remove(int id) {
        Bson filter = Filters.eq("id", id);

        this.transportMeans.findOneAndDelete(filter);
    }


    public long getSize() {
        // TODO: implementation
        return 0;
    }
}
