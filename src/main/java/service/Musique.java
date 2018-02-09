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
public class Musique {
    private String id;
    private String categorie;
    private String artiste;
    private String titre;
    private String fichier;
    private String paroles;

    public Musique(String id, String categorie, String artiste, String titre, String fichier, String paroles) {
        this.id = id;
        this.categorie = categorie;
        this.artiste = artiste;
        this.titre = titre;
        this.fichier = fichier;
        this.paroles = paroles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public String getParoles() {
        return paroles;
    }

    public void setParoles(String paroles) {
        this.paroles = paroles;
    }
}
