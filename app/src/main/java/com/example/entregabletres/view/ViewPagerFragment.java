package com.example.entregabletres.view;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.entregabletres.R;
import com.example.entregabletres.controller.AristaController;
import com.example.entregabletres.controller.ObraController;
import com.example.entregabletres.controller.PinturasController;
import com.example.entregabletres.model.Pojo.Artista;
import com.example.entregabletres.model.Pojo.ArtistaContainer;
import com.example.entregabletres.model.Pojo.ArtistaObraRecycler;
import com.example.entregabletres.model.Pojo.ArtistaROOM;
import com.example.entregabletres.model.Pojo.Obra;
import com.example.entregabletres.model.Pojo.ObraContainer;
import com.example.entregabletres.model.Pojo.ObraContainer_ROOM;
import com.example.entregabletres.model.Pojo.PinturasContainer;
import com.example.entregabletres.model.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {
    private List<Obra> listadoObras = new ArrayList<>();
    private List<Artista> listadoArtistas = new ArrayList<>();
    private Bundle bundle = new Bundle();
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private int i;
    public ViewPagerFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_view_pager, container, false);
        i=0;
        bundle = getArguments();
        viewPager = view.findViewById(R.id.view_pager_detalles4);
        getArtists();
        getObras();
        return view;
    }



    public void getArtists(){
                List<ArtistaROOM> listaDeArtistaRoom = new ArrayList<>();
                listaDeArtistaRoom = MainActivity.db.myDAO().getArtistas();

                for (ArtistaROOM track: listaDeArtistaRoom){
                    Artista artista = new Artista();
                    artista.setArtistId(track.getArtistId());
                    artista.setInfluencia(track.getInfluencia());
                    artista.setName(track.getName());
                    artista.setNationality(track.getNationality());
                    listadoArtistas.add(artista);
                }

            }


    public void getObras(){

        List<ObraContainer_ROOM> obras = MainActivity.db.myDAO().getObras();

            for (ObraContainer_ROOM track: obras) {
                Obra obra = new Obra();
                obra.setImage(track.getNameObra());
                obra.setName(track.getImage());
                obra.setArtistId(track.getArtistID());
                listadoObras.add(obra);
            }
                armarListaImagenes();
    }



    public void armarListaImagenes(){

            List<ArtistaObraRecycler> artistaObraRecyclerslista = new ArrayList<>();
            for (Obra track:listadoObras
            ) {
                for (Artista track2:listadoArtistas
                ) {
                    if(track.getArtistId().equals(track2.getArtistId())){
                        ArtistaObraRecycler artistaObraRecycler = new ArtistaObraRecycler();
                        artistaObraRecycler.setObra(track);
                        artistaObraRecycler.setArtista(track2);
                        artistaObraRecyclerslista.add(artistaObraRecycler);
                    }
                }
            }
            viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),artistaObraRecyclerslista );
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.setCurrentItem(bundle.getInt("posicion"));
        }

}
