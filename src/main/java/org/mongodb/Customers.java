package org.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;

public class Customers {
	ConnectionBase mon = new ConnectionBase();
	public DBCursor listCustomers() throws Exception{
		DBCursor cursor = null;
		try {
			DB db = mon.getConnection();
			DBCollection table = db.getCollection("customers");
			cursor = table.find();
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} catch(MongoException e){
			e.printStackTrace();
		}
		return cursor;		
	}
        
	public DBCursor findCustomers(String nom) throws Exception{
		DBCursor cursor = null;
		try {
			DB db = mon.getConnection();
			DBCollection table = db.getCollection("customers");
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nom", nom);

			cursor = table.find(searchQuery);
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} catch(MongoException e){
			e.printStackTrace();
		}
		return cursor;		
	}
        
	public DBCursor findCustomer() throws Exception{
		DBCursor cursor = null;
		try {
			DB db = mon.getConnection();
			DBCollection table = db.getCollection("customers");
			cursor = table.find();
			
		} catch(MongoException e){
			e.printStackTrace();
		}
		return cursor;		
	}
        
	public void insertCustomers(String nom, String prenom) throws Exception{
		try {
			DB db = mon.getConnection();
			DBCollection table = db.getCollection("customers");
			BasicDBObject document = new BasicDBObject();
			document.put("nom", nom);
			document.put("prenom", prenom);
			table.insert(document);
		} catch(MongoException e){
			e.printStackTrace();
		}
	}
	public void deleteCustomers(String nom) throws Exception{
		try {
			DB db = mon.getConnection();
			DBCollection table = db.getCollection("customers");
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nom", nom);
			table.remove(searchQuery);
		} catch(MongoException e){
			e.printStackTrace();
		}
	}
	public void updateCustomers(String nom, String rempl) throws Exception{
		DB db = mon.getConnection();
		DBCollection table = db.getCollection("customers");
		BasicDBObject query = new BasicDBObject();
		query.put("nom", nom);

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("nom", rempl);

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
		table.update(query, updateObj);
	}
}
