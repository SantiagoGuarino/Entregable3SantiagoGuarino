package com.example.entregabletres.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.entregabletres.R;
import com.example.entregabletres.model.Pojo.ArtistaROOM;


import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataBaseFragment extends Fragment {
public static MyAppDataBase myAppDataBase;

    public DataBaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_data_base, container, false);

        myAppDataBase= Room.databaseBuilder(getContext(),MyAppDataBase.class,"userdB").allowMainThreadQueries().build();
        List<ArtistaROOM> list = myAppDataBase.myDAO().getArtistas();
        int i = 0;
        return view;
    }

}
