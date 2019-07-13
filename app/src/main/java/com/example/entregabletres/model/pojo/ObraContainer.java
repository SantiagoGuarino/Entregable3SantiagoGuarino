package com.example.entregabletres.model.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObraContainer {
    @SerializedName("paints")
    private List<Obra> paints;
    public List<Obra> getPaints() {
        return paints;
    }
}
