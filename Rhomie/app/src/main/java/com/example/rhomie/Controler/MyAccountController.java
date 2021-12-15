package com.example.rhomie.Controler;

import android.app.Activity;

import com.example.rhomie.Models.IMyAccountModel;
import com.example.rhomie.Models.MyAccountModel;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.R;
import com.example.rhomie.View.IMyAccountView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyAccountController implements IMyAccountController, Observer {
    private IMyAccountModel model;
    private IMyAccountView view;

    public MyAccountController(IMyAccountView view){
        this.view = view;
        this.model = new MyAccountModel();

        ((Observable)model).addObserver(this);
    }

    @Override
    public void getItems() {
        model.getItems();
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Item> items = (ArrayList<Item>) arg;
        if (items != null){
            view.drawItems(items);
        }
    }
}
