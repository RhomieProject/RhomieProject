package com.example.rhomie.Models;


import androidx.annotation.NonNull;

import com.example.rhomie.Objects.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Observable;

public class ApartmentListModel extends Observable implements IApartmentListModel {

    DatabaseReference reference;
    public ApartmentListModel(){
        reference = FirebaseDatabase.getInstance().getReference("items");
    }

    @Override
    public void getItems() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> list = new ArrayList<>();
                for(DataSnapshot dts: snapshot.getChildren()){
                String value = dts.getValue(Item.class).itemToString(false);
                    if(value != null) {
                        list.add(value);
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
