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
public class User {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Coco
 */

    private String id;
    private String email;
    private String pseudo;
    private String mdp;
    private String sexe;
    private String nationalite;
    private String statut;

    public User(String id, String email, String pseudo, String mdp, String sexe, String nationalite, String statut) {
        this.id = id;
        this.email = email;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.sexe = sexe;
        this.nationalite = nationalite;
        this.statut = statut;
    }
     public User(String email, String pseudo, String mdp, String sexe, String nationalite, String statut) {
        
        this.email = email;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.sexe = sexe;
        this.nationalite = nationalite;
        this.statut = statut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}


