package com.example.entregabletres.model.Pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class ArtistaROOM {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "user_name")
    private String nombre;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
