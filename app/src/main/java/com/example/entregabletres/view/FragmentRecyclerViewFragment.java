package com.example.entregabletres.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.entregabletres.R;
import com.example.entregabletres.model.pojo.Artista;
import com.example.entregabletres.model.pojo.Obra;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecyclerViewFragment extends Fragment implements RecyclerViewFragmentAdapter.ObraSeleccionadaListener {
    public static final String KEY_OBRA = "obra";
    private List<Obra> listaDeObras = new ArrayList<>();
    private SelecccionarObraListener selecccionarObraListener;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    private ArrayList<Artista> listaDeArtistas = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewFragmentAdapter recyclerViewFragmentAdapter;
    public FragmentRecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        selecccionarObraListener = (SelecccionarObraListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        Bundle bundle = getArguments();
        recyclerView = view.findViewById(R.id.recycler_view_obras);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                ,RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        databaseReference = firebaseDatabase.getReference("artists");
        recyclerViewFragmentAdapter = new RecyclerViewFragmentAdapter
                ((List<Obra>) bundle.getSerializable(KEY_OBRA),listaDeArtistas, this);
        recyclerView.setAdapter(recyclerViewFragmentAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    Artista artistaEncontrado = data.getValue(Artista.class);
                    listaDeArtistas.add(artistaEncontrado);
                }
                recyclerViewFragmentAdapter.setArtistas(listaDeArtistas);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



        return view;
    }

    @Override
    public void obraSeleccionada(Integer position) {
        selecccionarObraListener.seleccionarObraListener(position);
    }
    public interface SelecccionarObraListener{
        void seleccionarObraListener(Integer position);
    }




}
