/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
public class CategorieDAO {
    ConnectionBase mon = new ConnectionBase();
        public Categorie[] listCategorie() throws Exception {            
            Categorie[] tabCat=null;
            Vector listMusic = new Vector();
            DBCursor cursor = null;
            try {
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("categorie");
                cursor = table.find();
                DBObject dObject=null;
                while (cursor.hasNext()) {
                    dObject = cursor.next();
                    String id = String.valueOf((ObjectId)(dObject.get("_id")));
                    String categorie = String.valueOf(dObject.get("categorie"));

                    Categorie temporaire = new Categorie(id, categorie);
                    listMusic.add(temporaire);
                }
                tabCat = new Categorie[listMusic.size()];
                listMusic.copyInto(tabCat);
            } catch(MongoException e){
                e.printStackTrace();
            }
            return tabCat;		
        }
}
