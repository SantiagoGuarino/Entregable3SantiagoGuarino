package com.example.entregabletres.view;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.entregabletres.R;
import com.example.entregabletres.model.Pojo.Mensaje;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.StorageReference;

public class MensajeAdapter extends FirestoreRecyclerAdapter<Mensaje, MensajeAdapter.MessageHolder> {
    private final String TAG = "MessageAdaper";
    Context context;
    String userId;
    StorageReference storageReference;
    private RequestOptions requestOptions = new RequestOptions();
    private final int MESSAGE_IN_VIEW_TYPE  = 1;
    private final int MESSAGE_OUT_VIEW_TYPE = 2;

    public MensajeAdapter(@NonNull Context context, Query query, String userID) {
        super(new FirestoreRecyclerOptions.Builder<Mensaje>()
        .setQuery(query, Mensaje.class)
        .build());

        userId = userID;
        }

    @Override
    public int getItemViewType(int position) {
        //if message userId matches current userid, set view type 1 else set view type 2
        if(getItem(position).getMessageUserId().equals(userId)){
            return MESSAGE_OUT_VIEW_TYPE;
        }
        return MESSAGE_IN_VIEW_TYPE;
    }

    public MensajeAdapter(@NonNull FirestoreRecyclerOptions<Mensaje> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MessageHolder messageHolder, int i, @NonNull Mensaje mensaje) {
        final TextView mText = messageHolder.mText;
        final TextView mUsername = messageHolder.mUsername;
        final TextView mTime = messageHolder.mTime;

        mUsername.setText(mensaje.getMessageUser());
        mText.setText(mensaje.getMessageText());
        mTime.setText(DateFormat.format("dd MMM  (h:mm a)", mensaje.getMessageTime()));

    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(viewType==MESSAGE_IN_VIEW_TYPE){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.celda_recycler_chat, parent, false);
        }
        if(viewType==MESSAGE_OUT_VIEW_TYPE){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.celda_recycler_chat_recibidos, parent, false);
        }
        return new MessageHolder(view);
    }


    public class MessageHolder extends RecyclerView.ViewHolder{
        TextView mText;
        TextView mUsername;
        TextView mTime;
        public MessageHolder(@NonNull View itemView) {
            super(itemView);
                mText = itemView.findViewById(R.id.text_celda_chat);
                mUsername = itemView.findViewById(R.id.username_celda_chat);
                mTime = itemView.findViewById(R.id.date_celda_chat);

        }
    }
}








