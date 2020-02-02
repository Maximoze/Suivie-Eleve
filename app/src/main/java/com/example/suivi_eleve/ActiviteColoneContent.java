package com.example.suivi_eleve;

public class ActiviteColoneContent  {

    public String libelle;
    public String type;
    public String date;

    public ActiviteColoneContent(String libelle, String type, String date){
        this.libelle = libelle;
        this.type = type;
        this.date = date;
    }


    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
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
}
