package com.example.rhomie.Models;

import com.example.rhomie.Objects.Item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddItemModel extends Observable {

    private DatabaseReference databaseReference;
    private DatabaseReference databaseUserToItem;

    public AddItemModel(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        this.databaseReference = db.getReference("items");
        this.databaseUserToItem = db.getReference("UserToItem");
    }

    public void addItem(Item item){
        DatabaseReference push = databaseReference.push();
                push.setValue(item)
                .addOnSuccessListener(suc->{
                    //success
                    addUserToItem(push,item);
                    setChanged();
                    notifyObservers(true);
                }).addOnFailureListener(fail->{
            //failed
            setChanged();
            notifyObservers(false);
        });
    }

    private void addUserToItem(DatabaseReference push,Item item){
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseUserToItem.child(user).child(push.getKey()).setValue(item);
    }

    public String getUser(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
    }


}
