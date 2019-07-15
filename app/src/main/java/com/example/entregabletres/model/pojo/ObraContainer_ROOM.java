package com.example.entregabletres.model.Pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "obras")
public class ObraContainer_ROOM {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name_obra")
    private String nameObra;
    @ColumnInfo(name = "artistID")
    private String artistID;
    @ColumnInfo(name = "image")
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameObra() {
        return nameObra;
    }

    public void setNameObra(String nameObra) {
        this.nameObra = nameObra;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }
}