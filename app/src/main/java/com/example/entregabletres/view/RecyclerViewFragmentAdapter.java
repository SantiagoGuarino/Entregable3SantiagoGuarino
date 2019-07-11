package com.example.entregabletres.view;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.entregabletres.R;
import com.example.entregabletres.model.pojo.Artista;
import com.example.entregabletres.model.pojo.Obra;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.http.Url;

public class RecyclerViewFragmentAdapter extends RecyclerView.Adapter<RecyclerViewFragmentAdapter.RecyclerView> {

    private List<Obra> listaDeObras;
    private ObraSeleccionadaListener obraSeleccionadaListener;
    private List<Artista> listaDeArtistas;
    private StorageReference mFirebaseStorage;
    private StorageReference myImgRef;
    private String exactMatch;

    public RecyclerViewFragmentAdapter(List<Obra> obras,List<Artista> listaDeArtistas, FragmentRecyclerViewFragment context){
        this.listaDeObras = obras;
        this.listaDeArtistas = listaDeArtistas;
        obraSeleccionadaListener = (ObraSeleccionadaListener) context;
        notifyDataSetChanged();
        mFirebaseStorage =  FirebaseStorage.getInstance().getReference("artistPaints");


    }

    @NonNull
    @Override
    public RecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celdas_recycler_obras, parent, false);
        RecyclerView fragmentRecyclerViewObras = new RecyclerView(view);
        return fragmentRecyclerViewObras;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView holder, int position) {
        Obra obra = listaDeObras.get(position);
        holder.bindObra(obra);
    }

    @Override
    public int getItemCount() {
        return listaDeObras.size();
    }

    public class RecyclerView extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        TextView textViewArtista;
        public RecyclerView(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    obraSeleccionadaListener.obraSeleccionada(getAdapterPosition());
                }
            });
            textView = itemView.findViewById(R.id.text_view_obras_celdas);
            imageView = itemView.findViewById(R.id.img_view_view_pager_celda);
            textViewArtista = itemView.findViewById(R.id.text_view_nombre_autor);
        }

        public void bindObra(Obra obra){
            textView.setText(obra.getName());
            bindObraYArtista(imageView, itemView,obra);
            }
    }

    public interface ObraSeleccionadaListener{
        void obraSeleccionada(Integer position);
    }

    public void setArtistas(List<Artista> artistas){
        listaDeArtistas = artistas;
        notifyDataSetChanged();
    }

    public static Boolean isContain(String source, String subItem){
        String pattern = "\\b"+subItem+"\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }

    public  void bindObraYArtista(final ImageView imageView, final View itemView, Obra obra){
        exactMatch = obra.getImage().substring(obra.getImage().lastIndexOf('/')+1
                , obra.getImage().lastIndexOf(".")).toLowerCase();
        myImgRef = mFirebaseStorage.child(exactMatch+".png");
        myImgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                for(Obra track: listaDeObras){
                    if (isContain(track.getImage(), exactMatch)){
                        Glide.with(itemView)
                                .load(uri)
                                .into(imageView);
                    }
                }
            }
        });
       /* for (Artista track: listaDeArtistas){
            for (String urlObra: track.getObras()){
                if(isContain(urlObra, exactMatch)){
                    Glide.with(itemView)
                            .load(urlObra)
                            .into(imageView);
                }
                }
            }*/

        }

    }

