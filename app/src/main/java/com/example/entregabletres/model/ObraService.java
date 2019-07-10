package com.example.entregabletres.model;

import com.example.entregabletres.model.pojo.ObraContainer;
import com.example.entregabletres.util.ObraHelper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ObraService {
    @GET(ObraHelper.BIN_ENDPOINT)
    Call<ObraContainer> getObras();

}
