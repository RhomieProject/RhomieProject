package com.example.rhomie.View;

import android.view.View;
import android.widget.TextView;

import com.example.rhomie.Objects.Item;

import java.util.ArrayList;

public interface IMyAccountView {


    public void drawItems(ArrayList<Item> items);
    public void getDetails(String greeting,String email,String fullName,String phoneNumber);
    public void getItemSuccess(String massage);
    public void getItemError(String massage);

}
