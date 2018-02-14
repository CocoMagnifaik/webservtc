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
public class CategorieController {
     public CategorieController(final CategorieDAO userService) {
Gson gson = new Gson();
    get("/listCategorie", (req, res) -> userService.listCategorie(), gson::toJson);

}
}

