package com.example.suivi_eleve.ui.Activites;

public class Model_Activites {

    private String libelle, type, date;
    private String imageUrl;
    long  classe;


    public Model_Activites(String libelle, String type, String date, String imageUrl, long classe) {
        this.libelle = libelle;
        this.type = type;
        this.date = date;
        this.imageUrl = imageUrl;
        this.classe = classe;
    }

    public long getClasse() {
        return classe;
    }

    public void setClasse(long classe) {
        this.classe = classe;
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


    public Model_Activites() {

    }
}
