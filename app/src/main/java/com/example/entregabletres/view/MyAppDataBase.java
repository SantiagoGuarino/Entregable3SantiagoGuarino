package com.example.entregabletres.view;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.entregabletres.model.Pojo.ArtistaROOM;
import com.example.entregabletres.model.Pojo.MyDAO;
import com.example.entregabletres.model.Pojo.ObraContainer_ROOM;


@Database(entities = {ArtistaROOM.class, ObraContainer_ROOM.class}, version = 1)
public abstract class MyAppDataBase extends RoomDatabase {
    public abstract MyDAO myDAO();
}
