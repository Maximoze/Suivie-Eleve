package com.example.suivi_eleve.Rappel;

public class Rappel {

    private String nom;
    private boolean expanded;
    private String id;
    long classe;

    public Rappel(){

    }

    public Rappel(String nom, String id, long classe) {
        this.nom = nom;
        this.id = id;
        this.classe = classe;
        this.expanded = false;
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

    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }


    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "Rappel{" +
                "nom='" + nom + '\'' +
                ", expanded=" + expanded +
                ", id='" + id + '\'' +
                ", classe=" + classe +
                '}';
    }
}