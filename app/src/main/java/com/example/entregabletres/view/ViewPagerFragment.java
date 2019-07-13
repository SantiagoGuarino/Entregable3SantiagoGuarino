package com.example.entregabletres.view;


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
import com.example.entregabletres.model.Pojo.Obra;
import com.example.entregabletres.model.Pojo.ObraContainer;
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
        traerArtistas();
        return view;
    }


    public void traerArtistas()  {
        AristaController aristaController = new AristaController();
        aristaController.getArtistas(new ResultListener<ArtistaContainer>() {
            @Override
            public void finish(ArtistaContainer result) {
               listadoArtistas = result.getArtists();
                getGeneros();
            }
        });
    }

    public void armarListaImagenes(){
        if(i<listadoObras.size()){
            traerImagenes(listadoObras.get(i).getImage());
        }else{
            i=0;
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
    public void traerImagenes(String adress)  {
        PinturasController pinturasController = new PinturasController();
        pinturasController.getPinturas(adress,new ResultListener<PinturasContainer>() {
            @Override
            public void finish(PinturasContainer result) {
                listadoObras.get(i).setImage(result.getPinturas().getUrl().toString());
                i++;
                armarListaImagenes();
            }
        });

    }


    public void getGeneros() {
        
        ObraController obrasController = new ObraController();
        obrasController.getObras(new ResultListener<ObraContainer>() {
            @Override
            public void finish(ObraContainer result) {

                listadoObras=result.getPaints();
                armarListaImagenes();
               // viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), listadoObras);

            }
        });

    }

}
