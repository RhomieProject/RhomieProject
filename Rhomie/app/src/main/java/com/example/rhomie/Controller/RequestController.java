package com.example.rhomie.Controller;

import com.example.rhomie.Models.RequestModel;
import com.example.rhomie.Objects.IItem;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.Request;
import com.example.rhomie.View.IRequestView;

import java.util.Observable;
import java.util.Observer;

public class RequestController implements Observer {
    private RequestModel model;
    private IRequestView view;

    public RequestController(IRequestView view){
        this.view = view;
        model = new RequestModel();
        model.addObserver(this);


    }

    public void addRequest(Item item){

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
