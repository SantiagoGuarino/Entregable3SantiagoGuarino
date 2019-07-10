package com.example.entregabletres.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.entregabletres.R;
import com.example.entregabletres.controller.ObraController;
import com.example.entregabletres.model.pojo.Artista;
import com.example.entregabletres.model.pojo.ObraContainer;
import com.example.entregabletres.util.ResultListener;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentRecyclerViewFragment.SelecccionarObraListener {
    private ViewPagerFragment viewPagerFragment = new ViewPagerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getObras();

    }

    public void pegarFragment(Fragment fragment){
        FragmentManager myFM = getSupportFragmentManager();
        FragmentTransaction myFT = myFM.beginTransaction();
        myFT.replace(R.id.container_main_activity, fragment);
        myFT.addToBackStack(null);
        myFT.commit();
    }

    public void getObras() {
        ObraController obrasController = new ObraController();
        obrasController.getObras(new ResultListener<ObraContainer>() {
            @Override
            public void finish(ObraContainer result) {
                FragmentRecyclerViewFragment fragmentRecyclerViewObras = new FragmentRecyclerViewFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(FragmentRecyclerViewFragment.KEY_OBRA, (Serializable) result.getPaints());
                fragmentRecyclerViewObras.setArguments(bundle);
                pegarFragment(fragmentRecyclerViewObras);

            }
        });
    }

    @Override
    public void seleccionarObraListener(Integer position) {
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_LISTENER, position);
        viewPagerFragment.setArguments(bundle);
        pegarFragment(viewPagerFragment);
    }


}
