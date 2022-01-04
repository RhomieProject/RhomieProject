package com.example.rhomie.Models;


import androidx.annotation.NonNull;

import com.example.rhomie.Objects.Item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Observable;

public class ApartmentListModel extends Observable implements IApartmentListModel {

    private DatabaseReference items;
    private String user;

    public ApartmentListModel(){
        items = FirebaseDatabase.getInstance().getReference("UserApartments");
        user = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @Override
    public void getItems() {
        items.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Item> list = new ArrayList<>();
                for(DataSnapshot userSS: snapshot.getChildren()){
                    for(DataSnapshot itemSS: userSS.getChildren()) {
                        Item item = itemSS.getValue(Item.class);
                        if (item.getIsAvailable() && !item.getFatherID().equals(user))
                            list.add(item);
                    }
                }
                setChanged();
                notifyObservers(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}
