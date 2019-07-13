package com.example.entregabletres.model;


import com.example.entregabletres.model.Pojo.ObraContainer;
import com.example.entregabletres.model.util.ObraHelper;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ObraService {
    @GET(ObraHelper.SEARCH_ENDPOINT)
    Call<ObraContainer> getObras();
}
