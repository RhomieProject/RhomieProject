package com.example.rhomie.Models;

import com.example.rhomie.Objects.IUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.atomic.AtomicBoolean;

public class SignUpModel implements ISignUpModel {
    private DatabaseReference databaseReference;

    public SignUpModel() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        this.databaseReference = db.getReference();
    }

    @Override
    public void init() {
    //TODO if we need that
    }

    @Override
    public boolean addUser(IUser user) {
        AtomicBoolean success = new AtomicBoolean(false);
        databaseReference.child(user.getID()).setValue(user).addOnSuccessListener(suc->{
            success.set(true);
        }).addOnFailureListener(fail->{
            success.set(false);
        });
        return success.get();
    }
}


