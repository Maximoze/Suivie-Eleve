package com.example.suivi_eleve;

import java.util.HashMap;

public class ActiviteColoneContent  {


    public String libelle;
    public String type;
    public String date;
    String ImageUrl;
    long classe;

    public ActiviteColoneContent(String libelle, String type, String date, String ImageUrl, long classe){
        this.libelle = libelle;
        this.type = type;
        this.date = date;
        this.ImageUrl = ImageUrl;
        this.classe = classe;
    }

    public long getClasse() {
        return classe;
    }

    public void setClasse(long classe) {
        this.classe = classe;

    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
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
