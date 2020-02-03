package com.example.suivi_eleve.ui.Activites;

public class Model_Activites {

    private String libelle, type, date;
    private String imageUrl;



    public Model_Activites(String libelle, String type, String date, String imageUrl) {
        this.libelle = libelle;
        this.type = type;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getimageUrl() {
        return imageUrl;
    }

    public void tempImageName(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Model_Activites() {

    }
}
