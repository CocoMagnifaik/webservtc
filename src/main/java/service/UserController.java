/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author CEDRICK
 */
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.*;

public class UserController {

public UserController(final Customers userService) {
Gson gson = new Gson();
get("/users", (req, res) -> userService.findtousCustomer(), gson::toJson);
get("/users/:id", (req, res) -> {

String id = req.params(":id");

Customer[] user=null;
    try {
        user = userService.findCustomerById(id);
    } catch (Exception ex) {
        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
    }

if (user != null) {

return user;

}

res.status(400);

return new ResponseError("No user with id '%s' found", id);

}, gson::toJson);



}

}

/*public UserControllerByid(final Customers userService) {
Gson gson = new Gson();
get("/users/:id", (req, res) -> {

String id = req.params(":id");

Customer user = userService.getUser(id);

if (user != null) {

return user;

}

res.status(400);

return new ResponseError("No user with id '%s' found", id);

}, gson::toJson);


}
}
*/