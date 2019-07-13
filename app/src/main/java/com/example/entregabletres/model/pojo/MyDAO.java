package com.example.entregabletres.model.Pojo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDAO {
    @Insert
    public void addUser(ArtistaROOM artistaROOM);
    @Query("select * from users")
    public List<ArtistaROOM> getUser();
}
