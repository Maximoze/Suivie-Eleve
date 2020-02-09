package com.example.suivi_eleve.ObservationsEnseignant;

public class Recup {

    String nom, id;

    public Recup() {

    }

    public Recup(String nom,String id) {
        this.nom = nom;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "nom='" + nom + '\'' +
                ", id='" + id + '\'';
    }
}
