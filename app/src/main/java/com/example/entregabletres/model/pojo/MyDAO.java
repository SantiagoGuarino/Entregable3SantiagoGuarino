package com.example.entregabletres.model.Pojo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDAO {

    @Insert
    public void addArtistas(ArtistaROOM artista_room);

    @Query("select * from artistas")
    public List<ArtistaROOM> getArtistas();

    @Query("delete from artistas")
    public void deleteArtistas ();


    @Insert
    public void addObras(ObraContainer_ROOM artista_room);

    @Query("select * from obras")
    public List<ObraContainer_ROOM> getObras();

    @Query("delete from obras")
    public void deleteObras ();




/*
@Insert
public void addObras(ObraContainer_ROOM obraContainer_room);

@Insert
public void addPinturas(PinturasContainer_ROOM pinturasContainer_room);


@Query("select * from obras")
public ObraContainer getObras();

@Query("select * from pinturas")
public PinturasContainer getPinturas();

@Query("delete from artistas")
public void deleteArtistas ();

@Query("delete from obras")
public void deleteObra ();

@Query("delete from pinturas")
public void deletePinturas ();*/


}
