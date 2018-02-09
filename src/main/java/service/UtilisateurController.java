/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.*;

/**
 *
 * @author CEDRICK
 */
public class UtilisateurController {
    
public UtilisateurController(final UserDAO Utilisateurservice) {
    
Gson gson = new Gson();
get("/utilisateurs", (req, res) -> Utilisateurservice.listUser(), gson::toJson);
get("/findUserById/:id", (req, res) -> {

String id = req.params(":id");
User[] user=null;
    try {
        user = Utilisateurservice.findUserById(id);
    } catch (Exception ex) {
        Logger.getLogger(UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
    }
if (user != null) {
return user;
}
res.status(400);
return new ResponseError("No user with id '%s' found", id);
},  gson::toJson);


    
post ( "/createUser" , ( req , res ) ->  Utilisateurservice.insertUsers(req.queryParams ( "email" ),
        req.queryParams ( "pseudo" ),
        req.queryParams ( "mdp" ),
        req.queryParams ( "sexe" ),
        req.queryParams ( "nationalite" ),
        req.queryParams ( "status" )
), gson::toJson);

}
}
