/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import static spark.Spark.get;

/**
 *
 * @author CEDRICK
 */
public class MusiqueController {
    public MusiqueController(final MusiqueDAO userService) {
Gson gson = new Gson();
get("/musiqueTotal", (req, res) -> userService.listMusique(), gson::toJson);
get("/findMusicById/:id", (req, res) -> {

String id = req.params(":id");
String  zika=null;
    try {
        zika = userService.findMusicById(id);
    } catch (Exception ex) {
        Logger.getLogger(MusiqueController.class.getName()).log(Level.SEVERE, null, ex);
    }
if (zika != null) {
return zika;
}
res.status(400);
return new ResponseError("No user with id '%s' found", id);

}, gson::toJson);
get("/findTitreMusicById/:id", (req, res) -> {

String id = req.params(":id");

String titreMusicId = userService.findTitreMusicById(id);

if (titreMusicId != null) {

return titreMusicId;

}

res.status(400);

return new ResponseError("No user with id '%s' found", id);

}, gson::toJson);
}
}
