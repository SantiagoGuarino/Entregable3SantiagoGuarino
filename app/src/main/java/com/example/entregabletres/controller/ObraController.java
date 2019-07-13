package com.example.entregabletres.controller;

import com.example.entregabletres.model.Dao.ObraDAO;
import com.example.entregabletres.model.Pojo.ObraContainer;
import com.example.entregabletres.model.util.ResultListener;

public class ObraController {


public void getObras (final ResultListener<ObraContainer> listener){
    ObraDAO obraDAO =new ObraDAO();
    obraDAO.getObras(new ResultListener<ObraContainer>() {
        @Override
        public void finish(ObraContainer result) {
            listener.finish(result);
        }
    });
}
}
