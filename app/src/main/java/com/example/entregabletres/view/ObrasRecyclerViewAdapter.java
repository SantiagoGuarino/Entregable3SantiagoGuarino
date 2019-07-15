package com.example.entregabletres.view;

import android.content.Context;

import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.entregabletres.R;
import com.example.entregabletres.model.Pojo.Obra;
import java.util.ArrayList;
import java.util.List;

public class ObrasRecyclerViewAdapter extends RecyclerView.Adapter<ObrasRecyclerViewAdapter.ObrasRecyclerViewPager> {
private List<Obra> listObras;
private SeleccionarObraListener seleccionarObraListener;
private Boolean aBoolean;


public void setObrasAdapter (List<Obra> obras){
    listObras = obras;
    notifyDataSetChanged();
}


    public ObrasRecyclerViewAdapter(List<Obra> listadoDeObras, FragmentRecyclerObras context, Boolean aBoolean) {
        listObras=new ArrayList<>();
        this.listObras = listadoDeObras;
        seleccionarObraListener=(SeleccionarObraListener)context;
        this.aBoolean = aBoolean;
    }

    @NonNull
    @Override
    public ObrasRecyclerViewPager onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.celdas_obras,viewGroup,false);
       ObrasRecyclerViewPager obrasRecyclerViewPager = new ObrasRecyclerViewPager(view);
        return obrasRecyclerViewPager;
    }

    @Override
    public void onBindViewHolder(@NonNull ObrasRecyclerViewPager obrasRecyclerViewPager, int i) {
    Obra obra = listObras.get(i);
    obrasRecyclerViewPager.bindObras(obra);
    }

    @Override
    public int getItemCount() {
        return listObras.size();
    }

    public class ObrasRecyclerViewPager extends RecyclerView.ViewHolder{
    private TextView nombreObra;
    private ImageView imagenObra;
        public ObrasRecyclerViewPager(@NonNull View itemView) {
            super(itemView);
            nombreObra=itemView.findViewById(R.id.nombre_obra);
            imagenObra=itemView.findViewById(R.id.imageView_obra);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Obra obra = listObras.get(getAdapterPosition());
                  seleccionarObraListener.seleccionarObraListener(obra,getAdapterPosition());
                                    }
            });
        }
    public void bindObras(Obra obra){
            if (aBoolean = true){
                nombreObra.setText(obra.getName().toString());
                Glide.with(itemView.getContext())
                        .load(obra.getImage())
                        .into(imagenObra);
            } else{
                nombreObra.setText(obra.getName().toString());
                Glide.with(itemView.getContext())
                        .load(obra.getImage())
                        .onlyRetrieveFromCache(true)
                        .into(imagenObra);
            }
        }
    }



    public interface SeleccionarObraListener{
        public void seleccionarObraListener(Obra obra, int position);
    }
}
