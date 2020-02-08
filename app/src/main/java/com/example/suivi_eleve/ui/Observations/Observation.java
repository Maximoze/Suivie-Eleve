package com.example.suivi_eleve.ui.Observations;

public class Observation {

    String date, nomEleve, noteContent, typeContent;

    public Observation(){{
    }
    }

    public Observation(String date, String nomEleve, String noteContent, String typeContent) {
        this.date = date;
        this.nomEleve = nomEleve;
        this.noteContent = noteContent;
        this.typeContent = typeContent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNomEleve() {
        return nomEleve;
    }

    public void setNomEleve(String nomEleve) {
        this.nomEleve = nomEleve;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "date='" + date + '\'' +
                ", nomEleve='" + nomEleve + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", typeContent='" + typeContent + '\'' +
                '}';
    }
}
