package service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import org.bson.types.ObjectId;

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
        public Customer[] findtousCustomer() {
            
		Customer[] tabCustomers=null;
                Vector listMed = new Vector();
                DBCursor cursor = null;
		try {
			DB db = mon.getConnection();
			DBCollection table = db.getCollection("customers");
			cursor = table.find();
                        DBObject dObject=null;
			while (cursor.hasNext()) {
                            dObject=cursor.next();
			String id=String.valueOf((ObjectId)(dObject.get("_id")));
                        String nom=String.valueOf(dObject.get("nom"));
                        String prenom=String.valueOf(dObject.get("prenom"));
                                
                            System.out.print("tt"+dObject.get("_id"));
                            Customer temporaire = new Customer(id,nom,prenom);
				listMed.add(temporaire);
                        }
		
	
		tabCustomers = new Customer[listMed.size()];
		listMed.copyInto(tabCustomers);
		
		} catch(MongoException e){
			e.printStackTrace();
		}
				return tabCustomers;
		
        }
          public Customer[] findCustomerById(String ids) throws Exception {            
            Customer[] tabCustomers=null;
            Vector listUser = new Vector();
            DBCursor cursor = null;
            try {
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("customers");
                BasicDBObject searchQuery = new BasicDBObject("_id", new ObjectId(ids));
                cursor = table.find(searchQuery);
                DBObject dObject = null;
                while (cursor.hasNext()) {
                    dObject = cursor.next();
                    String id = String.valueOf((ObjectId)(dObject.get("_id")));
                    String email = String.valueOf(dObject.get("nom"));
                    String pseudo = String.valueOf(dObject.get("prenom"));
                   

                    System.out.print("ID: "+dObject.get("_id"));
                    Customer temporaire = new Customer(id, email, pseudo);
                    listUser.add(temporaire);
                }
                tabCustomers = new Customer[listUser.size()];
                listUser.copyInto(tabCustomers);
            } catch(MongoException e){
                e.printStackTrace();
            }
            return tabCustomers;		
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
