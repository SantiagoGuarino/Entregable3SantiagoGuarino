package com.example.entregabletres.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.entregabletres.R;
import com.example.entregabletres.controller.AristaController;
import com.example.entregabletres.controller.PinturasController;
import com.example.entregabletres.model.Pojo.Artista;
import com.example.entregabletres.model.Pojo.ArtistaContainer;
import com.example.entregabletres.model.Pojo.Obra;
import com.example.entregabletres.model.Pojo.Pinturas;
import com.example.entregabletres.model.Pojo.PinturasContainer;
import com.example.entregabletres.model.util.ResultListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Url;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecyclerObras extends Fragment implements ObrasRecyclerViewAdapter.SeleccionarObraListener{
    public final static String KEY_DATOS = "datos";
    private SeleccionarObraListener seleccionarObraListener;
    private int i;
    private  RecyclerView recyclerView;
    private  Bundle bundle;
    private StorageReference pathReference;
    private List<Artista> artistas;
    private List<Pinturas> pinturas;
    private List<Obra> obras;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;
private   ObrasRecyclerViewAdapter obrasRecyclerViewAdapter;
    private StorageReference mFirebaseStorage;
    private StorageReference myImgRef;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        seleccionarObraListener=(SeleccionarObraListener)context;
    }

    public FragmentRecyclerObras() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_fragment_recycler_obras, container, false);
         i=0;
         artistas = new ArrayList<>();
         pinturas = new ArrayList<>();
         obras = new ArrayList<>();
         bundle = getArguments();
         obras = (List<Obra>) bundle.getSerializable(KEY_DATOS);
        recyclerView = view.findViewById(R.id.recyclerViewObras);
        obrasRecyclerViewAdapter= new ObrasRecyclerViewAdapter(obras,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(obrasRecyclerViewAdapter);
        traerArtistas();


         return view;
    }

    @Override
    public void seleccionarObraListener(Obra obra, int position) {
        seleccionarObraListener.seleccionarObraListener(obra, position);
    }





    public void traerArtistas()  {
        AristaController aristaController = new AristaController();
        aristaController.getArtistas(new ResultListener<ArtistaContainer>() {
            @Override
            public void finish(ArtistaContainer result) {
                artistas = result.getArtists();
                armarListaImagenes();
            }
        });



    }
    public void armarListaImagenes(){
        if(i<obras.size()){
            traerImagenes(obras.get(i).getImage());
        }else{
            i=0;
            obrasRecyclerViewAdapter.setObrasAdapter(obras);
        }
    }

    public void traerImagenes(String adress)  {
        PinturasController pinturasController = new PinturasController();
        pinturasController.getPinturas(adress,new ResultListener<PinturasContainer>() {
            @Override
            public void finish(PinturasContainer result) {
            obras.get(i).setImage(result.getPinturas().getUrl().toString());
            i++;
            armarListaImagenes();
            }
        });

    }


    public interface SeleccionarObraListener{
        public void seleccionarObraListener(Obra obra, int position);
    }
}
