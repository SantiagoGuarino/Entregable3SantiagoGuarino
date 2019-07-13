package com.example.entregabletres.view;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.entregabletres.model.Pojo.ArtistaROOM;
import com.example.entregabletres.model.Pojo.MyDAO;



@Database(entities = {ArtistaROOM.class}, version = 1)
public abstract class MyAppDataBase extends RoomDatabase {
    public abstract MyDAO myDAO();
}
