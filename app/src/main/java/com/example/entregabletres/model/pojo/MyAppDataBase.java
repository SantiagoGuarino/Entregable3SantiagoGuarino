package com.example.entregabletres.model.Pojo;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {ArtistaROOM.class, ObraContainer_ROOM.class}, version =2)
public abstract  class MyAppDataBase extends RoomDatabase {
    public abstract MyDAO myDAO();
}

