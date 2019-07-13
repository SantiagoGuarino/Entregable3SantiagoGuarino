package com.example.entregabletres.controller;

import com.example.entregabletres.model.Dao.PinturasDAO;
import com.example.entregabletres.model.Pojo.PinturasContainer;
import com.example.entregabletres.model.util.ResultListener;

public class PinturasController {

    public void getPinturas (String adress,final ResultListener<PinturasContainer> listener){
        PinturasDAO pinturasDAO =new PinturasDAO();
        pinturasDAO.traerImagenes(adress, new ResultListener<PinturasContainer>() {
            @Override
            public void finish(PinturasContainer result) {
                listener.finish(result);
            }
        });
    }
}
