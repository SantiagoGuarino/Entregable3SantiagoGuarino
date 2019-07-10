package com.example.entregabletres.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.entregabletres.model.pojo.Obra;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<FragmentDetallesObras> listaDeFragmentDetalles;

    public ViewPagerAdapter(FragmentManager fm, List<Obra> listaDeObras) {
        super(fm);
        listaDeFragmentDetalles = new ArrayList<>();
        for (Obra track: listaDeObras){
            listaDeFragmentDetalles.add(FragmentDetallesObras.fragmentDetallesObras(track));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return listaDeFragmentDetalles.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragmentDetalles.size();
    }
}
