package com.example.suivi_eleve;

public class PhotoColoneContent {

    public String description;
    public String ImageUrl;

    public PhotoColoneContent(String description,  String  ImageUrl) {
        this.description = description;
        this.ImageUrl = ImageUrl;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public PhotoColoneContent (){

    }
}
