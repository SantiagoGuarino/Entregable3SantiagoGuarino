package com.example.entregabletres.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.entregabletres.model.Pojo.ArtistaObraRecycler;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<FragmentDetallesObras> fragmentDetallesObras;


    public ViewPagerAdapter(FragmentManager fm,List<ArtistaObraRecycler> fragmentDetallesArtistas) {
        super(fm);
        this.fragmentDetallesObras=new ArrayList<>();
        for (ArtistaObraRecycler track: fragmentDetallesArtistas
        ) {
            this.fragmentDetallesObras.add(FragmentDetallesObras.generadorFragmentObra(track));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentDetallesObras.get(position);
    }

    @Override
    public int getCount() {
        return fragmentDetallesObras.size();
    }
}
