package com.example.rhomie.Models;

import com.example.rhomie.Objects.Item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddItemModel extends Observable {

    private DatabaseReference items;

    public AddItemModel(){
        this.items = FirebaseDatabase.getInstance().getReference("UserApartments");
    }

    public void addItem(Item item){
        DatabaseReference push = items.child(getUser()).push();
        item.setItem(push.child(push.getKey()).getKey());
        push.setValue(item)
        .addOnSuccessListener(suc->{
            //success
            setChanged();
            notifyObservers(true);
        }).addOnFailureListener(fail->{
            //failed
            setChanged();
            notifyObservers(false);
        });
    }

    public String getUser(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


}
