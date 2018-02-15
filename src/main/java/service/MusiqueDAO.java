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
                    String image = String.valueOf(dObject.get("image"));
                    String paroles = String.valueOf(dObject.get("paroles"));
                    String minutage = String.valueOf(dObject.get("minutage"));
                    String valiny = String.valueOf(dObject.get("valiny"));

                    Musique temporaire = new Musique(id, categorie, artiste, titre, fichier, image, paroles, minutage, valiny);
                    listMusic.add(temporaire);
                }
                tabMusique = new Musique[listMusic.size()];
                listMusic.copyInto(tabMusique);
            } catch(MongoException e){
                e.printStackTrace();
            }
            return tabMusique;		
        }
    
     public Musique[] findMusique(String ids, String titre) throws Exception {            
            Musique[] tabMusique=null;
            Vector listMusic = new Vector();
            DBCursor cursor = null;
            try {
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("chanson");
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("titre", titre);
                cursor = table.find(searchQuery);
                DBObject dObject=null;
                while (cursor.hasNext()) {
                    dObject = cursor.next();
                    ids = String.valueOf((ObjectId)(dObject.get("_id")));
                    String categorie = String.valueOf(dObject.get("categorie"));
                    String artiste = String.valueOf(dObject.get("artiste"));
                    String titres = String.valueOf(dObject.get("titre"));
                    String fichier = String.valueOf(dObject.get("fichier"));
                    String image = String.valueOf(dObject.get("image"));
                    String paroles = String.valueOf(dObject.get("paroles"));
                    String minutage = String.valueOf(dObject.get("minutage"));
                    String valiny = String.valueOf(dObject.get("valiny"));

                    Musique temporaire = new Musique(ids, categorie, artiste, titres, fichier, image, paroles, minutage, valiny);
                    listMusic.add(temporaire);
                }
                tabMusique = new Musique[listMusic.size()];
                listMusic.copyInto(tabMusique);
            } catch(MongoException e){
                e.printStackTrace();
            }
            return tabMusique;		
        }
     
       public String findFichierById(String ids) throws Exception {            
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
                    tabMusique = String.valueOf(dObject.get("fichier"));
                }
            } catch(MongoException e){
                e.printStackTrace();
            }
            return tabMusique;		
        }

     
      public String findParoleById(String ids) throws Exception {            
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

    public void insertMusique(String categorie, String artiste, String titre, String fichier, String image, String paroles, String minutage, String valiny) throws Exception{
        try {
            DB db = mon.getConnection();
            DBCollection table = db.getCollection("chanson");
            BasicDBObject document = new BasicDBObject();
            document.put("categorie", categorie);
            document.put("artiste", artiste);
            document.put("titre",titre);
            document.put("fichier",fichier);
            document.put("image",image);
            document.put("paroles",paroles);
            document.put("minutage",minutage);
            document.put("valiny",valiny);
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
