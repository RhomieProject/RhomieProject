package com.example.rhomie.Controler;

import com.example.rhomie.Models.AddItemModel;
import com.example.rhomie.Objects.Address;
import com.example.rhomie.Objects.Flags;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.View.AddItemView;

import java.util.Date;
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

    public void OnAddItem(Address address, Flags flags, String check_in, String check_out, String guest_number){

        Item item = new Item(0,address, flags, check_in, check_out, guest_number);
        int addItemCode = item.isValid();
/*
        if(addItemCode == 1)
            view.AddItemError("Is not a date");
        if(addItemCode == 2)
            view.AddItemError("Is not a date");
*/
        if(addItemCode == 3)
            view.AddItemError("Guest Number is required");
        if(addItemCode == 4)
            view.AddItemError("City is required");
        if(addItemCode == 5)
            view.AddItemError("Street is required");
        if(addItemCode == 6)
            view.AddItemError("Street Number is required");
        if(addItemCode == -1)
            model.addItem(item);
    }

    @Override
    public void update(Observable o, Object arg) {

        if((boolean) arg){
            //success
            view.AddItemSuccess("Successfully added item");
        }else{
            //failed
            view.AddItemError("Failed added item");
        }
    }
}
