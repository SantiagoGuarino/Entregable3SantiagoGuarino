package com.example.entregabletres.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.entregabletres.R;
import com.example.entregabletres.controller.ObraController;
import com.example.entregabletres.model.Pojo.ArtistaROOM;
import com.example.entregabletres.model.Pojo.Obra;
import com.example.entregabletres.model.Pojo.ObraContainer;
import com.example.entregabletres.model.util.ResultListener;
import com.example.entregabletres.view.login.LogInActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements FragmentRecyclerObras.SeleccionarObraListener {
private Button logoutButton;
public static MyAppDataBase db;
private RecyclerChat recyclerChat = new RecyclerChat();
private NavigationView navigationView;
private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext()
                , MyAppDataBase.class, "artistas_y_obras").allowMainThreadQueries().build();


       navigationView = findViewById(R.id.navigation_view);
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               itemSelected(menuItem);
               return true;
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

    private void itemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                break;
            case R.id.chat:
                pegarFragment(recyclerChat);
        }
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
                pegarFragment(new FragmentRecyclerObras());
            }
        });

    }

    public void pegarFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_container, fragment);
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
