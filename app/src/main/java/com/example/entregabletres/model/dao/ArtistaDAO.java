package com.example.entregabletres.model.Dao;


import com.example.entregabletres.model.Pojo.Artista;
import com.example.entregabletres.model.Pojo.ArtistaContainer;
import com.example.entregabletres.model.util.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistaDAO   {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;


    public ArtistaDAO() {
    }

public void getArtist(final ResultListener<ArtistaContainer> listener){
    final List<Artista> artistas = new ArrayList<>();
    final ArtistaContainer artistaContainer = new ArtistaContainer();

    myRef = database.getReference("artists");
    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot data :dataSnapshot.getChildren()) {
                Artista jugadorEncontrado = data.getValue(Artista.class);
                 artistas.add(jugadorEncontrado);
            }
            artistaContainer.setArtists(artistas);
            listener.finish(artistaContainer);
       }        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
        }
    });

}




}

