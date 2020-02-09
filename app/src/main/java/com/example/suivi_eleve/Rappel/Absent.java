package com.example.suivi_eleve.Rappel;

public class Absent {
    String typeContent ;
    String noteContent ;
    String nomEleve;
    String date;
    public Absent(){

    }

    public Absent(String typeContent, String noteContent, String nomEleve, String date) {
        this.typeContent = typeContent;
        this.noteContent = noteContent;
        this.nomEleve = nomEleve;
        this.date = date;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getNomEleve() {
        return nomEleve;
    }

    public void setNomEleve(String nomEleve) {
        this.nomEleve = nomEleve;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "typeContent='" + typeContent + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", nomEleve='" + nomEleve + '\'' +
                '}';
    }
}
