package com.example.entregabletres.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.entregabletres.R;
import com.example.entregabletres.controller.ObraController;
import com.example.entregabletres.model.Pojo.Obra;
import com.example.entregabletres.model.Pojo.ObraContainer;
import com.example.entregabletres.model.util.ResultListener;
import com.example.entregabletres.view.login.LogInActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements FragmentRecyclerObras.SeleccionarObraListener {
private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*DataBaseFragment database = new DataBaseFragment();
        pegarFragment(database);*/


        logoutButton=findViewById(R.id.button_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
            }
        });

        FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(intent);

                }
            }
        });
        getGeneros();



    }
    public void getGeneros() {
        ObraController obrasController = new ObraController();
        obrasController.getObras(new ResultListener<ObraContainer>() {
            @Override
            public void finish(ObraContainer result) {
                FragmentRecyclerObras fragmentRecyclerViewObras = new FragmentRecyclerObras();
                Bundle bundle = new Bundle();
                bundle.putSerializable(FragmentRecyclerObras.KEY_DATOS, (Serializable) result.getPaints());
                fragmentRecyclerViewObras.setArguments(bundle);
                pegarFragment(fragmentRecyclerViewObras);
            }
        });

    }
    public void pegarFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_container4, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void seleccionarObraListener(Obra obra, int position) {
       // FragmentDetallesObras fragmentDetallesObras = new FragmentDetallesObras();
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("position",obra);
        bundle.putInt("posicion",position);
        viewPagerFragment.setArguments(bundle);
        pegarFragment(viewPagerFragment);
        // fragmentDetallesObras.setArguments(bundle);
       // pegarFragment(fragmentDetallesObras);
    }
}
