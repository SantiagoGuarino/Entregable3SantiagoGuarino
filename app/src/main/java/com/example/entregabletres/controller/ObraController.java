package com.example.entregabletres.controller;

import com.example.entregabletres.model.dao.ObraDAO;
import com.example.entregabletres.model.pojo.ObraContainer;
import com.example.entregabletres.util.ResultListener;

public class ObraController {

    public void getObras(final ResultListener<ObraContainer> listenerDeLaView){
        ObraDAO obraDAO = new ObraDAO();
        obraDAO.getObra(new ResultListener<ObraContainer>() {
            @Override
            public void finish(ObraContainer result) {
                listenerDeLaView.finish(result);
            }
        });
    }
}
