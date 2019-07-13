package com.example.entregabletres.model.Dao;

import android.net.Uri;
import android.widget.Toast;

import com.example.entregabletres.model.Pojo.Pinturas;
import com.example.entregabletres.model.Pojo.PinturasContainer;
import com.example.entregabletres.model.util.ResultListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PinturasDAO {
    private StorageReference mFirebaseStorage;
    private StorageReference myImgRef;


    public void traerImagenes(String adress, final ResultListener<PinturasContainer> listener)  {
        mFirebaseStorage =  FirebaseStorage.getInstance().getReference();
    //    myImgRef = mFirebaseStorage.child("artistPaints/michelangelo_eveandadam.png");
        myImgRef = mFirebaseStorage.child(adress);
        myImgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                PinturasContainer pinturasContainer = new PinturasContainer();
                Pinturas pinturas = new Pinturas();
                pinturas.setUrl(uri);
                pinturasContainer.setPinturas(pinturas);
                listener.finish(pinturasContainer);
            }
        });


    }
}
