package com.example.rhomie.Models;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.IUser;
import com.example.rhomie.Objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Observable;

public class SettingModel extends Observable {

    private DatabaseReference users;

    private FirebaseUser user;

    private String details;


    public SettingModel(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void getDetails() {
        users.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                IUser userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    String email = userProfile.getEmail();
                    String firstName = userProfile.getFirstName();
                    String lastName = userProfile.getLastName();
                    String phoneNumber = userProfile.getPhoneNumber();
                    String id = userProfile.getID();
                    details = email+'/'+firstName+'/'+lastName+'/'+phoneNumber+'/'+id+'/';
                }
                setChanged();
                notifyObservers(details);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    public void Logout() {
        FirebaseAuth.getInstance().signOut();
    }

}
