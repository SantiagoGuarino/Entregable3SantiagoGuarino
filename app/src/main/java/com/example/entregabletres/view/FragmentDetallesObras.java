package com.example.entregabletres.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.entregabletres.R;
import com.example.entregabletres.model.pojo.Artista;
import com.example.entregabletres.model.pojo.Obra;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetallesObras extends Fragment {
    public static final String KEY_ARTISTAS = "artistas";
    public static final String KEY_DETALLES = "detalles";
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    private Obra obra;
    private ImageView imageView;
    private TextView textViewNombreObra;
    private TextView textViewNombreArtista;
    private TextView textViewInfluencia;
    private List<Artista> listaDeArtistas = new ArrayList<>();

    public static FragmentDetallesObras fragmentDetallesObras(Obra obra) {
        FragmentDetallesObras fragmentDetallesObras= new FragmentDetallesObras();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_DETALLES, obra);
        fragmentDetallesObras.setArguments(bundle);
        return fragmentDetallesObras;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detales_obras, container, false);
        Bundle bundle = getArguments();
        obra = (Obra) bundle.getSerializable(KEY_DETALLES);
        listaDeArtistas = new ArrayList<>();
        //Acá está el error
        databaseReference = firebaseDatabase.getReference("artists");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    Artista artistaEncontrado = data.getValue(Artista.class);
                    listaDeArtistas.add(artistaEncontrado);
                    bindObraYArtista(imageView, obra);
                    textViewNombreObra.setText(obra.getName());

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        imageView = view.findViewById(R.id.img_view_detalles);
        textViewNombreObra = view.findViewById(R.id.nombre_obra_detalles);
        textViewNombreArtista = view.findViewById(R.id.nombre_artista_detalles);
        textViewInfluencia = view.findViewById(R.id.influencia_detalles);
        return view;
    }

    public  void bindObraYArtista(ImageView imageView, Obra obra){
        View itemView = getView();
        String exactMatch = obra.getImage().substring(obra.getImage().lastIndexOf('/')+1
                , obra.getImage().lastIndexOf(".")).toLowerCase();
        for (Artista track: listaDeArtistas){
            for (String urlObra: track.getObras()){
                if(isContain(urlObra, exactMatch)){
                    Glide.with(itemView)
                            .load(urlObra)
                            .into(imageView);
                    textViewNombreArtista.setText(track.getName());
                    textViewInfluencia.setText(track.getInfluencia());
                }if(obra.getName().equals("Mila a la napo")){
                    Glide.with(itemView)
                            .load("https://firebasestorage.googleapis.com/v0/b/entregabletres-cd8ae.appspot.com/o/andy_milanapo.png?alt=media&token=9e1175e8-f86e-4751-944c-fd95f23b3af9")
                            .into(imageView);
                    textViewNombreArtista.setText(track.getName());
                    textViewInfluencia.setText(track.getInfluencia());
                }
            }
        }
    }
    public static Boolean isContain(String source, String subItem){
        String pattern = "\\b"+subItem+"\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }


}
