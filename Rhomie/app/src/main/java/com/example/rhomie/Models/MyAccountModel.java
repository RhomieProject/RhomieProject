package com.example.rhomie.Models;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.IUser;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Observable;

public class MyAccountModel extends Observable implements IMyAccountModel {

    private DatabaseReference databaseReference;
    private FirebaseUser user;

    public MyAccountModel(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("UserToItem");

        user = FirebaseAuth.getInstance().getCurrentUser();
    }


    @Override
    public void getItems() {
        databaseReference.child("ol9IqSmDlTb9JqcR1C8FdQ35mBx2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Item> items = new ArrayList<>();
                for (DataSnapshot dts: snapshot.getChildren()) {
                        items.add(dts.getValue(Item.class));
                }
                setChanged();
                notifyObservers(items);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
