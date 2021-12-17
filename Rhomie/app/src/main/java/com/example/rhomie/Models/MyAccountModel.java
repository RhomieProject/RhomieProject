package com.example.rhomie.Models;

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

    private DatabaseReference databaseReference, databaseUser;
    private FirebaseUser user;
    private String details;

    public MyAccountModel(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("UserToItem");
        databaseUser = db.getReference("users");
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void getItems() {
        databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> items = new ArrayList<>();
                for (DataSnapshot dts: snapshot.getChildren()) {
                        items.add(dts.getValue(Item.class).itemToString(true));
                }
                setChanged();
                notifyObservers(items);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }

    @Override
    public void getDetails() {
        databaseUser.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                IUser userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    String email = userProfile.getEmail();
                    String fullName = userProfile.getFullName();
                    String phoneNumber = userProfile.getPhoneNumber();
                    details = email + '/'  + fullName + '/' + phoneNumber + '/';
                }
                setChanged();
                notifyObservers(details);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    @Override
    public void Logout() {
        FirebaseAuth.getInstance().signOut();
    }

}
