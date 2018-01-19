package org.mongodb;

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
    private int id;
    private String nom;
    private String prenom;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
