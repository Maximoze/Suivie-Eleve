package com.example.suivi_eleve.Rappel;

public class Eleve {
    String nom;
    private String id;
    long classe;

    public Eleve( long classe, String id ,String nom) {
        this.id = id;
        this.classe = classe;
        this.nom = nom;
    }

    public Eleve(){

    }

    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getClasse() {
        return classe;
    }

    public void setClasse(long classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "Eleve{" +
                "nom='" + nom + '\'' +
                ", id='" + id + '\'' +
                ", classe='" + classe + '\'' +
                '}';
    }
}
