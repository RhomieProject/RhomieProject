package com.example.rhomie.Controller;

import com.example.rhomie.Models.IMyAccountModel;
import com.example.rhomie.Models.MyAccountModel;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.View.IMyAccountView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyAccountController implements IMyAccountController, Observer {
    private IMyAccountModel model;
    private IMyAccountView view;
    private FirebaseUser user;
    private DatabaseReference reference;
    ArrayList<String> d = new ArrayList<>();

    public MyAccountController(IMyAccountView view){
        this.view = view;
        this.model = new MyAccountModel();
        ((Observable)model).addObserver(this);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
    }

    @Override
    public void getItems() {
        model.getItems();
    }

    @Override
    public void getDetails() {
        model.getDetails();
    }

    @Override
    public void Logout(){
        model.Logout();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.getClass().equals(ArrayList.class)){
            ArrayList<Item> items = (ArrayList<Item>) arg;
            if(items != null)
                view.drawItems(items);
        }
        else if(arg.getClass().equals(String.class)) {
            String details = (String) arg;
            if(details != null){
                splitString(details);
                String email = d.get(0);
                String fullName = d.get(1);
                String phoneNumber = d.get(2);
                view.getDetails("Welcome " + fullName + "!", email, fullName, phoneNumber);
            }
        }
    }

    private void splitString(String s) {
        char[] chars = s.toCharArray();
        String word = "";
        for(char c : chars) {
            if(c!= '/') {
                word += c;
            }
            else {
                d.add(word);
                word = "";
            }
        }
    }


}
