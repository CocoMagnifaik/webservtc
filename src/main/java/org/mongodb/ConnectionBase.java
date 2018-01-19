package org.mongodb;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class ConnectionBase {
    DB db;
    public DB getConnection() throws Exception{
        MongoClient mongo = new MongoClient("localhost", 27017);
        return db = mongo.getDB("mycustomers");
    }
}
