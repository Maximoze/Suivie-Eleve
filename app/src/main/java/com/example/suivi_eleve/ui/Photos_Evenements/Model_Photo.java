package com.example.suivi_eleve.ui.Photos_Evenements;

public class Model_Photo {

    String descritption, imageUrl;

    public Model_Photo(){

    }

    public Model_Photo(String descritption, String imageUrl) {
        this.descritption = descritption;
        this.imageUrl = imageUrl;
    }

    public String getDescritption() {
        return descritption;
    }

    public void setDescritption(String descritption) {
        this.descritption = descritption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Info{" +
                "descritption='" + descritption + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

