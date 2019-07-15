package com.example.entregabletres.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.entregabletres.R;
import com.example.entregabletres.controller.AristaController;
import com.example.entregabletres.controller.ObraController;
import com.example.entregabletres.controller.PinturasController;
import com.example.entregabletres.model.Pojo.Artista;
import com.example.entregabletres.model.Pojo.ArtistaContainer;
import com.example.entregabletres.model.Pojo.ArtistaROOM;
import com.example.entregabletres.model.Pojo.Obra;
import com.example.entregabletres.model.Pojo.ObraContainer;
import com.example.entregabletres.model.Pojo.ObraContainer_ROOM;
import com.example.entregabletres.model.Pojo.Pinturas;
import com.example.entregabletres.model.Pojo.PinturasContainer;
import com.example.entregabletres.model.util.ResultListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecyclerObras extends Fragment implements ObrasRecyclerViewAdapter.SeleccionarObraListener{
    public final static String KEY_DATOS = "datos";
    private SeleccionarObraListener seleccionarObraListener;
    private int i;
    private RecyclerView recyclerView;
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
    private ArtistaROOM artistaROOM;
    private Bitmap bitmap;
    private String source;
    private MainActivity mainActivity = new MainActivity();
    private Context context;


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
        context = getContext();
        bundle = getArguments();
        recyclerView = view.findViewById(R.id.recyclerViewObras);
        obrasRecyclerViewAdapter= new ObrasRecyclerViewAdapter(obras,this, isOnline());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(obrasRecyclerViewAdapter);

        if(isOnline()) {
            getObrasOnline();
        }else{
            cargaOffline();
        }
        return view;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public void getObrasOnline() {
        ObraController obrasController = new ObraController();
        obrasController.getObras(new ResultListener<ObraContainer>() {
            @Override
            public void finish(ObraContainer result) {
                obras = result.getPaints();
                obrasRecyclerViewAdapter.setObrasAdapter(obras);
                traerArtistas();
            }
        });
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
            arrangeROOM_Artist();
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
                for(Obra track: obras){
                    source = track.getImage();
                }
            }
        });

    }


    public void cargaOffline(){
        getObrasOffline();
    }


    @Override
    public void seleccionarObraListener(Obra obra, int position) {
        seleccionarObraListener.seleccionarObraListener(obra, position);
    }





    public void arrangeROOM_Artist(){
        deleteARtista();
        ArtistaROOM artistaROOM = new ArtistaROOM();
        addArtist(artistaROOM);
    }


    public void arrangeROOM_Obras(){
        deleteObras();
        ObraContainer_ROOM obraContainer_room = new ObraContainer_ROOM();
        addObras(obraContainer_room);
    }

    public void deleteARtista(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                MainActivity.db.myDAO().deleteArtistas();
            }
        });
    }

    public void addArtist(final ArtistaROOM artistaROOM){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                for (Artista track:artistas
                ) {
                    artistaROOM.setNationality(track.getNationality());
                    artistaROOM.setName(track.getName());
                    artistaROOM.setInfluencia(track.getInfluencia());
                    artistaROOM.setArtistId(track.getArtistId());
                    MainActivity.db.myDAO().addArtistas(artistaROOM);
                }
                arrangeROOM_Obras();
            }
        });
    }

    public void getObrasOffline(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(!isOnline()){
                    List<ObraContainer_ROOM> obras =MainActivity.db.myDAO().getObras();
                    List<Obra> listObraApasar = new ArrayList<>();

                    for (ObraContainer_ROOM track: obras
                    ) {
                        Obra obra = new Obra();
                        obra.setImage(track.getNameObra());
                        obra.setName(track.getImage());
                        obra.setArtistId(track.getArtistID());
                        listObraApasar.add(obra);
                    }
                    obrasRecyclerViewAdapter.setObrasAdapter(listObraApasar);
                }

            }
        });
    }

    public void deleteObras(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                MainActivity.db.myDAO().deleteObras();
                //getArtists();
            }
        });
    }

    public void addObras(final ObraContainer_ROOM obrasRoom){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                for (Obra track:obras
                ) {
                    obrasRoom.setArtistID(track.getArtistId());
                    obrasRoom.setNameObra(track.getImage());
                    obrasRoom.setImage(track.getName());
                    MainActivity.db.myDAO().addObras(obrasRoom);
                }
            }
        });
    }


    public void getArtists(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                MainActivity.db.myDAO().getArtistas();

            }
        });
    }







    public interface SeleccionarObraListener{
        public void seleccionarObraListener(Obra obra, int position);
    }


}
