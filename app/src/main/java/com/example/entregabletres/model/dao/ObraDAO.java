package com.example.entregabletres.model.dao;
import com.example.entregabletres.model.MyRetrofit;
import com.example.entregabletres.model.ObraService;
import com.example.entregabletres.model.pojo.ObraContainer;
import com.example.entregabletres.util.ObraHelper;
import com.example.entregabletres.util.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObraDAO extends MyRetrofit {
    private ObraService obraService;

    public ObraDAO() {
        super(ObraHelper.BASE_URL);
        obraService = retrofit.create(ObraService.class);
    }

    public void getObra(final ResultListener<ObraContainer> listenerDelController){
        Call<ObraContainer> call = obraService.getObras();
        call.enqueue(new Callback<ObraContainer>() {
            @Override
            public void onResponse(Call<ObraContainer> call, Response<ObraContainer> response) {
                ObraContainer obraContainer = response.body();
                listenerDelController.finish(obraContainer);
            }

            @Override
            public void onFailure(Call<ObraContainer> call, Throwable t) {

            }
        });

    }
}
