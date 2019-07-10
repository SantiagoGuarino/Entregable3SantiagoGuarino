package com.example.entregabletres.model.pojo;

import java.io.Serializable;

public class Obra implements Serializable {
    private String image;
    private String name;
    private String artistId;

    public Obra(String image, String name, String artistId) {
        this.image = image;
        this.name = name;
        this.artistId = artistId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
}