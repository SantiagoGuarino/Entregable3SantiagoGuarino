package com.example.entregabletres.controller;

import android.content.Context;

import com.example.entregabletres.model.Dao.ArtistaDAO;
import com.example.entregabletres.model.Pojo.ArtistaContainer;
import com.example.entregabletres.model.util.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AristaController    {

    public void getArtistas (final ResultListener<ArtistaContainer> listener){
        ArtistaDAO artistaDAO =new ArtistaDAO();
        artistaDAO.getArtist(new ResultListener<ArtistaContainer>() {
            @Override
            public void finish(ArtistaContainer result) {
                listener.finish(result);
            }
        });

    }





}
