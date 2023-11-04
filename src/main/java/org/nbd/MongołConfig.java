package org.nbd;

import com.mongodb.ConnectionString;

public class MongołConfig {
    private static ConnectionString connectionString = new ConnectionString(
            "mongodb://localhost:27017,localhost:27018,localhost:27019/?replicaSet=replica_set_single");
}
