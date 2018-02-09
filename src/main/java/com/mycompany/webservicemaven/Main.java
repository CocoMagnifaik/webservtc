/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webservicemaven;

/**
 *
 * @author CEDRICK
 */

import service.Customers;
import service.JeuController;
import service.JeuDAO;
import service.MusiqueController;
import service.MusiqueDAO;
import service.UserController;
import service.UserDAO;
import service.UtilisateurController;
import static spark.Spark.*;


public class Main {

public static void main(String[] args) {
    port(getHerokuAssignedPort());
   // port(getHerokuAssignedPort());
   String origin,methods,headers;
   origin="*";
   methods="GET,PUT,DELETE,OPTIONS,POST";
   headers="Access-Control-Allow-Origin, "+"+Content-Type,Access-Control-Allow-Headers,Authorization,X-Requested-With";
new UserController(new Customers());
new UtilisateurController(new UserDAO());
new MusiqueController(new MusiqueDAO());
new JeuController(new JeuDAO());
    enableCORS(origin,methods,headers);
}
static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    private static void enableCORS(final String origin,final String  methods,final String headers){
    
    options("/*" ,(request,response) -> {
        String accesControlRequestHeaders=request.headers("Access-Control-Request-Headers");
        if(accesControlRequestHeaders != null ){
            response.header("Access-Control-Request-Headers",accesControlRequestHeaders);
        }
        String accesControlRequestMethod=request.headers("Acces-Control-Request-Methods");
         if(accesControlRequestMethod != null ){
            response.header("Access-Control-Request-Methods",accesControlRequestMethod);
        }
         return "OK";
        
      });
      
      before((request,response) ->{
            response.header("Access-Control-Allow-Origin",origin);
              response.header("Access-Control-Request-Methods",origin);
                response.header("Access-Control-Allow-Headers",origin);
                  response.type("application/json");
    });
      
  }
}