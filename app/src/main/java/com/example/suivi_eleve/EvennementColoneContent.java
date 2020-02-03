package com.example.suivi_eleve;

class EvennementColoneContent {
    public String libelle;
    public String type;
    public String date;
    public String description;
    public String heure;
    public String lieu;
    public String cotisation;

    public EvennementColoneContent(String libelle, String type, String date, String description, String heure, String lieu, String cotisation) {
        this.libelle = libelle;
        this.type = type;
        this.date = date;
        this.description = description;
        this.heure = heure;
        this.lieu = lieu;
        this.cotisation = cotisation;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getCotisation() {
        return cotisation;
    }

    public void setCotisation(String cotisation) {
        this.cotisation = cotisation;
    }

}
