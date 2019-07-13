package com.example.entregabletres.model.Dao;

import com.example.entregabletres.model.MyRetrofit;
import com.example.entregabletres.model.ObraService;
import com.example.entregabletres.model.Pojo.ObraContainer;
import com.example.entregabletres.model.util.ObraHelper;
import com.example.entregabletres.model.util.ResultListener;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObraDAO extends MyRetrofit {
    private ObraService service;
    public ObraDAO() {
        super(ObraHelper.BASE_URL);
        service = retrofit.create(ObraService.class);
    }

    public void getObras (final ResultListener<ObraContainer> listener){
    Call<ObraContainer> call =service.getObras();
    call.enqueue(new Callback<ObraContainer>() {
        @Override
        public void onResponse(Call<ObraContainer> call, Response<ObraContainer> response) {
            ObraContainer obraContainer = response.body();
            listener.finish(obraContainer);
        }
        @Override
        public void onFailure(Call<ObraContainer> call, Throwable t) {
        }
    });
}

}
