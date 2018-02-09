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
 * @author Coco
 */
public class MusiqueDAO {
    ConnectionBase mon = new ConnectionBase();
        public Musique[] listMusique(){            
            Musique[] tabMusique=null;
            Vector listMusic = new Vector();
            DBCursor cursor = null;
            try {
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("chanson");
                cursor = table.find();
                DBObject dObject=null;
                while (cursor.hasNext()) {
                    dObject = cursor.next();
                    String id = String.valueOf((ObjectId)(dObject.get("_id")));
                    String categorie = String.valueOf(dObject.get("categorie"));
                    String artiste = String.valueOf(dObject.get("artiste"));
                    String titre = String.valueOf(dObject.get("titre"));
                    String fichier = String.valueOf(dObject.get("fichier"));
                    String paroles = String.valueOf(dObject.get("paroles"));

                    Musique temporaire = new Musique(id, categorie, artiste, titre, fichier, paroles);
                    listMusic.add(temporaire);
                }
                tabMusique = new Musique[listMusic.size()];
                listMusic.copyInto(tabMusique);
            } catch(MongoException e){
                e.printStackTrace();
            }
            return tabMusique;		
        }
    
        public String findMusicById(String ids)  {            
            String tabMusique=null;
            DBCursor cursor = null;
            try {
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("chanson");
                cursor = table.find();
                DBObject dObject=null;
                while (cursor.hasNext()) {
                    dObject = cursor.next();
                    ids = String.valueOf((ObjectId)(dObject.get("_id")));
                    tabMusique = String.valueOf(dObject.get("paroles"));
                }
            } catch(MongoException e){
                e.printStackTrace();
            }
            return tabMusique;		
        }

        public String findTitreMusicById(String ids) {            
            String tabMusique=null;
            DBCursor cursor = null;
            try {
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("chanson");
                cursor = table.find();
                DBObject dObject=null;
                while (cursor.hasNext()) {
                    dObject = cursor.next();
                    ids = String.valueOf((ObjectId)(dObject.get("_id")));
                    tabMusique = String.valueOf(dObject.get("titre"));
                }
            } catch(MongoException e){
                e.printStackTrace();
            }
            return tabMusique;		
        }

    public void insertMusique(String categorie, String artiste, String titre, String fichier, String paroles) throws Exception{
        try {
            DB db = mon.getConnection();
            DBCollection table = db.getCollection("chanson");
            BasicDBObject document = new BasicDBObject();
            document.put("categorie", categorie);
            document.put("artiste", artiste);
            document.put("titre",titre);
            document.put("fichier",fichier);
            document.put("paroles",paroles);
            table.insert(document);
        } catch(MongoException e){
            e.printStackTrace();
        }
    }
    
    	public void deleteMusique(String id) throws Exception{
            try {
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("chanson");
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("_id", id);
                table.remove(searchQuery);
            } catch(MongoException e){
                e.printStackTrace();
            }
	}
        
        public void updateMusique(String paroles, String rempl) throws Exception{
            DB db = mon.getConnection();
            DBCollection table = db.getCollection("chanson");
            BasicDBObject query = new BasicDBObject();
            query.put("paroles", paroles);

            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("paroles", rempl);

            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);
            table.update(query, updateObj);
	}
}
