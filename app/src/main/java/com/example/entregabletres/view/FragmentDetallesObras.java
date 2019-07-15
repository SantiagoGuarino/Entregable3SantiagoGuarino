package com.example.entregabletres.view;


import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.entregabletres.R;
import com.example.entregabletres.model.Pojo.Artista;
import com.example.entregabletres.model.Pojo.ArtistaObraRecycler;
import com.example.entregabletres.model.Pojo.Obra;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetallesObras extends Fragment {
private TextView nombreObra;
private TextView nacionalidadObra;
private TextView influenciaObra;
private TextView nombreArtista;
private ImageView imagenObra;

private int position;
private List<Obra> listadoObras = new ArrayList<>();
private List<Artista> listadeArtistas = new ArrayList<>();
    final static String KEY_NOMBRE_NACIONALIDAD = "nacionalidad";
    final static String KEY_NOMBRE_ARTISTA = "nombreArtista";
    final static String KEY_NOMBRE_INFLUENCED = "influenciados";
    final static String KEY_NOMBRE_OBRA = "nombreObra";
    final static String KEY_IMAGEN_OBRA = "imagenObra";


    public FragmentDetallesObras() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_detalles_obras, container, false);

        imagenObra=view.findViewById(R.id.imagen_detalle_artista);
        nombreObra=view.findViewById(R.id.nombre_detalle_artista);
        nombreArtista = view.findViewById(R.id.nombre_artista_detalle_artista);
        influenciaObra = view.findViewById(R.id.influencia_detalle_artista);
        nacionalidadObra = view.findViewById(R.id.nacionalidad_detalle_artista);

        Bundle bundle = getArguments();
        Glide.with(getContext())
                .load(bundle.getString(KEY_IMAGEN_OBRA))
                .onlyRetrieveFromCache(true)
                .into(imagenObra);
        nombreObra.setText(bundle.getSerializable(KEY_NOMBRE_ARTISTA).toString());
        nombreArtista.setText(bundle.getSerializable(KEY_NOMBRE_ARTISTA).toString());
        influenciaObra.setText(bundle.getSerializable(KEY_NOMBRE_INFLUENCED).toString());
        nacionalidadObra.setText(bundle.getSerializable(KEY_NOMBRE_NACIONALIDAD).toString());
        return view;
    }

    public static FragmentDetallesObras generadorFragmentObra(ArtistaObraRecycler artista){
        FragmentDetallesObras fragmentDetallesObras = new FragmentDetallesObras();
        Bundle bundle = new Bundle();
        bundle.putString(FragmentDetallesObras.KEY_NOMBRE_OBRA,artista.getObra().getName());
        bundle.putString(FragmentDetallesObras.KEY_IMAGEN_OBRA,artista.getObra().getImage());
        bundle.putString(FragmentDetallesObras.KEY_NOMBRE_NACIONALIDAD,artista.getArtista().getNationality());
        bundle.putString(FragmentDetallesObras.KEY_NOMBRE_ARTISTA,artista.getArtista().getName());
        bundle.putString(FragmentDetallesObras.KEY_NOMBRE_INFLUENCED,  artista.getArtista().getInfluencia());
        fragmentDetallesObras.setArguments(bundle);
        return fragmentDetallesObras;
    }






}
