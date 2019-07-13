package com.example.entregabletres.model.Pojo;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Artista implements Serializable {
    @SerializedName("Influenced_by")
    private String influencia;
    private String artistId;
    private String name;
    private String nationality;
   /* @SerializedName("pinturas")
    private List<ArtistaPintura> pinturas;*/


    public Artista(){

    }

   /* public List<ArtistaPintura> getPinturas() {
        return pinturas;
    }

    public void setPinturas(List<ArtistaPintura> pinturas) {
        this.pinturas = pinturas;
    }*/

    @PropertyName("Influenced_by")
    public String getInfluencia() {
        return influencia;
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

    @PropertyName("Influenced_by")
    public void setInfluencia(String influencia) {
        this.influencia = influencia;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


}
