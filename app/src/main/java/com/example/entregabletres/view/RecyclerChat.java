package com.example.entregabletres.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.entregabletres.R;
import com.example.entregabletres.model.Pojo.Mensaje;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerChat extends Fragment {
    private FirestoreRecyclerAdapter<Mensaje, MensajeAdapter.MessageHolder> adapter;
    private EditText input;
    private String userName;
    private Button send;
    private EditText textoAEnviar;
    private FirebaseFirestore database;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Query query;
    private String userId;

    public RecyclerChat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_chat, container, false);
        send = view.findViewById(R.id.enviar_chat);
        //textoAEnviar = view.findViewById(R.id.edit_text_recycler_chat_room);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_chat_room);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        input = view.findViewById(R.id.edit_text_recycler_chat_room);
        userName = user.getDisplayName();
        userId = user.getUid();
        database = FirebaseFirestore.getInstance();
        query = database.collection("messages").orderBy("messageTime");
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()) {
                    //pgBar.setVisibility(View.GONE);
                }
            }

        });

        adapter = new MensajeAdapter(getContext(), query, userId);
        recyclerView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = input.getText().toString();
                if(TextUtils.isEmpty(message)){
                    Toast.makeText(getContext(), "Post is post", Toast.LENGTH_LONG).show();
                    return;
                }
                database.collection("messages").add(new Mensaje(userName, message, userId, 0, null));
                input.setText("");
            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(adapter!=null)
            adapter.stopListening();
    }
}
