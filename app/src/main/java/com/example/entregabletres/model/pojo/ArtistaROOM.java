package com.example.entregabletres.model.Pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "artistas")
public class ArtistaROOM {
    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "influencia")
    private String influencia;
    @ColumnInfo(name = "artistaID")
    private String artistId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "nacionality")
    private String nationality;
    @ColumnInfo(name = "uri")
    private String uri;



    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfluencia() {
        return influencia;
    }

    public void setInfluencia(String influencia) {
        this.influencia = influencia;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}


