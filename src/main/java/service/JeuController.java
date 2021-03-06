/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author CEDRICK
 */
public class JeuController {
    public JeuController(final JeuDAO userService) {
Gson gson = new Gson();
get("/listJeu", (req, res) -> userService.listJeu(), gson::toJson);
get("/findJeuById/:id", (req, res) -> {

String id = req.params(":id");

String gme=null;
    try {
        gme = userService.findJeuById(id);
    } catch (Exception ex) {
        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
    }

if (gme != null) {

return gme;

}

res.status(400);

return new ResponseError("No user with id '%s' found", id);

}, gson::toJson);

get("/createGame/:idJoueur/:idChanson/:points", (req, res) -> userService.insertJeu(
req.queryParams(":idJoueur"),
req.queryParams(":idChanson"),
req.queryParams(":points")

), gson::toJson);

after((req, res) -> {
			res.type("application/json");
		});
                
		

}
}
