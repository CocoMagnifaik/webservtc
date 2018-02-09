package service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Coco
 */
public class Customer {
    private String id;
    private String nom;
    private String prenom;
    
    
        public Customer(String idd, String name, String prenom)
        {

            initialise(idd, name, prenom);

        }

        public void initialise(String idd, String name, String prenom)
        {
            this.setId(idd);
            this.setNom (name);
            this.setPrenom (prenom);
            
          



        }
    public String getId() {
        return id;
    }
    public void setId(String id) {
            this.id = id;
    }

    public String getNom() {
            return nom;
    }
    public void setNom(String nom) {
            this.nom = nom;
    }

    public String getPrenom() {
            return prenom;
    }
    public void setPrenom(String prenom) {
            this.prenom = prenom;
    }
}
