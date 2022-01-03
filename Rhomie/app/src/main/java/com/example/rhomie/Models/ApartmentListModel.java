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
    private FirebaseUser user;

    public ApartmentListModel(){
        items = FirebaseDatabase.getInstance().getReference("items");
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void getItems() {
        items.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Item> list = new ArrayList<>();
                for(DataSnapshot dts: snapshot.getChildren()){
                    Item item = dts.getValue(Item.class);
                    if(item.getIsAvailable() && !item.getFatherID().equals(user.getUid())) {
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
