package com.example.suivi_eleve.ui.Evenements;

public class Model_Evenement {
    private String libelle, type, date ,description, lieu, heure, cotisation;




    public Model_Evenement(String libelle, String type, String date, String description,String lieu, String heure, String cotisation ) {
        this.libelle = libelle;
        this.type = type;
        this.date = date;
        this.description = description;
        this.lieu = lieu;
        this.heure = heure;
        this.cotisation = cotisation;


    }

    public String getLibelle() {
        return libelle;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getLieu() {
        return lieu;
    }

    public String getHeure() {
        return heure;
    }

    public String getCotisation() {
        return cotisation;
    }

    public Model_Evenement() {

    }
}
