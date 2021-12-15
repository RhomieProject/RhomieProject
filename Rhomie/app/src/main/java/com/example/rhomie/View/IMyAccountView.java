package com.example.rhomie.View;

import com.example.rhomie.Objects.Item;

import java.util.ArrayList;

public interface IMyAccountView {

    public void drawItems(ArrayList<Item> items);
    public void getItemSuccess(String massage);
    public void getItemError(String massage);

}
