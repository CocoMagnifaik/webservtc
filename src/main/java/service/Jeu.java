/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Coco
 */
public class Jeu {
    private String id;
    private String idJoueur;
    private String idChanson;
    private String points;

    public Jeu(String id, String idJoueur, String idChanson, String points) {
        this.id = id;
        this.idJoueur = idJoueur;
        this.idChanson = idChanson;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(String idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getIdChanson() {
        return idChanson;
    }

    public void setIdChanson(String idChanson) {
        this.idChanson = idChanson;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
