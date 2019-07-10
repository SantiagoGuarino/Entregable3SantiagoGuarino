package com.example.entregabletres.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Artista implements Serializable {
    private String Influenced_by;
    private String artistId;
    private String name;
    private String nationality;
    private List<String> obras;

    public Artista(){

    }

    public Artista(String Influenced_by, String artistId, String name, String nationality, List<String> obras) {
        this.Influenced_by = Influenced_by;
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
        this.obras = obras;
    }

    public String getInfluencia() {
        return Influenced_by;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public List<String> getObras() {
        return obras;
    }
}
