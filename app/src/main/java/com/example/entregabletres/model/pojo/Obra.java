package com.example.entregabletres.model.Pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Obra implements Serializable {
    private String image;
    private String name;
    private String artistId;

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    private List<String> url = new ArrayList<>();


    public List<String> getUrl() {
        return url;
    }

    public void addURLList (String url){
        this.url.add(url);
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
