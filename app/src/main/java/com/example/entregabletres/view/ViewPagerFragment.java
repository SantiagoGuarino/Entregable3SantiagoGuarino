package com.example.entregabletres.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.entregabletres.R;
import com.example.entregabletres.controller.ObraController;
import com.example.entregabletres.model.pojo.Obra;
import com.example.entregabletres.model.pojo.ObraContainer;
import com.example.entregabletres.util.ResultListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {
    public static final String KEY_LISTENER = "listener";
    private List<Obra> listaDeObras;
    private ViewPager viewPager;
    public ViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        Bundle bundle = getArguments();
        listaDeObras = new ArrayList<>();

        getObras();
        viewPager = view.findViewById(R.id.view_pager_fragment_detalles);
        return view;
    }

    public void getObras() {
        ObraController obrasController = new ObraController();
        obrasController.getObras(new ResultListener<ObraContainer>() {
            @Override
            public void finish(ObraContainer result) {
                listaDeObras = result.getPaints();
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), listaDeObras );
                viewPager.setAdapter(viewPagerAdapter);
            }
        });
    }

}
