package com.example.rhomie.Controler;

import com.example.rhomie.Models.AddItemModel;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.View.AddItemView;

import java.util.Observable;
import java.util.Observer;

public class AddItemController implements Observer {

    private AddItemModel model;
    private AddItemView view;

    public AddItemController(AddItemView activity){
        this.view = activity;
        this.model = new AddItemModel();
        model.addObserver(this);
    }

    public void OnAddItem(){
        Item newItem = new Item();
//        Item newItem = new Item(0, new Address(),new Flags(),new Date(), new Date(), 0);
        model.addItem(newItem);

    }

    @Override
    public void update(Observable o, Object arg) {

        if((boolean) arg){
            //success
            view.AddItemSuccess("success to add item");
        }else{
            //failed
            view.AddItemError("failed to add item");
        }
    }
}
