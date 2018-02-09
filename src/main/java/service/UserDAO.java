/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import java.util.Vector;
import org.bson.types.ObjectId;
/**
 *
 * @author CEDRICK
 */
public class UserDAO {
    	ConnectionBase mon = new ConnectionBase();
        
        public User[] listUser() {            
            User[] tabCustomers=null;
            Vector listUser = new Vector();
            DBCursor cursor = null;
            try {
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("users");
                cursor = table.find();
                DBObject dObject=null;
                while (cursor.hasNext()) {
                    dObject = cursor.next();
                    String id = String.valueOf((ObjectId)(dObject.get("_id")));
                    String email = String.valueOf(dObject.get("email"));
                    String pseudo = String.valueOf(dObject.get("pseudo"));
                    String mdp = String.valueOf(dObject.get("mdp"));
                    String sexe = String.valueOf(dObject.get("sexe"));
                    String nationalite = String.valueOf(dObject.get("nationalite"));
                    String statut = String.valueOf(dObject.get("statut"));

                    System.out.print("ID: "+dObject.get("_id"));
                    User temporaire = new User(id, email, pseudo, mdp, sexe, nationalite, statut);
                    listUser.add(temporaire);
                }
                tabCustomers = new User[listUser.size()];
                listUser.copyInto(tabCustomers);
            } catch(MongoException e){
                e.printStackTrace();
            }
            return tabCustomers;		
        }

        public User[] findUserById(String ids) {            
            User[] tabCustomers=null;
            Vector listUser = new Vector();
            DBCursor cursor = null;
            try {
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("users");
                BasicDBObject searchQuery = new BasicDBObject("_id", new ObjectId(ids));
                cursor = table.find(searchQuery);
                DBObject dObject = null;
                while (cursor.hasNext()) {
                    dObject = cursor.next();
                    String id = String.valueOf((ObjectId)(dObject.get("_id")));
                    String email = String.valueOf(dObject.get("email"));
                    String pseudo = String.valueOf(dObject.get("pseudo"));
                    String mdp = String.valueOf(dObject.get("mdp"));
                    String sexe = String.valueOf(dObject.get("sexe"));
                    String nationalite = String.valueOf(dObject.get("nationalite"));
                    String statut = String.valueOf(dObject.get("statut"));

                    System.out.print("ID: "+dObject.get("_id"));
                    User temporaire = new User(id, email, pseudo, mdp, sexe, nationalite, statut);
                    listUser.add(temporaire);
                }
                tabCustomers = new User[listUser.size()];
                listUser.copyInto(tabCustomers);
            } catch(MongoException e){
                e.printStackTrace();
            }
            return tabCustomers;		
        }

        public DBCursor findUsers(String pseudo,String mdp) {
            DBCursor cursor = null;
            try {
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("users");
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("pseudo", pseudo);
                searchQuery.put("mdp",mdp);

                cursor = table.find(searchQuery);
                while (cursor.hasNext()) {
                        System.out.println(cursor.next());
                }
            } catch(MongoException e){
                e.printStackTrace();
            }
            return cursor;		
	}
                
	public User insertUsers(String email, String pseudo, String mdp, String sexe, String nationalite, String statut) {
             User us=null;
            try {
                //User us=null;
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("users");
                BasicDBObject document = new BasicDBObject();
                document.put("email", email);
                document.put("pseudo", pseudo);
                document.put("mdp",mdp);
                document.put("sexe",sexe);
                document.put("nationalite",nationalite);
                document.put("statut",statut);
                table.insert(document);
                us=User( email,  pseudo,  mdp,  sexe,  nationalite,  statut);
            } catch(MongoException e){
                e.printStackTrace();
            }
            return us;
	}
	public void deleteUsers(String email) throws Exception{
            try {
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("users");
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("email", email);
                table.remove(searchQuery);
            } catch(MongoException e){
                e.printStackTrace();
            }
	}
        
	public void updateUsers(String pseudo, String rempl) throws Exception{
            DB db = mon.getConnection();
            DBCollection table = db.getCollection("users");
            BasicDBObject query = new BasicDBObject();
            query.put("pseudo", pseudo);

            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("pseudo", rempl);

            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);
            table.update(query, updateObj);
	}

    private User User(String email, String pseudo, String mdp, String sexe, String nationalite, String statut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
