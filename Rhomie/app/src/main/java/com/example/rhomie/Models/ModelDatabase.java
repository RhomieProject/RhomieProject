package com.example.rhomie.Models;

import com.example.rhomie.Objects.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModelDatabase {

    private DatabaseReference databaseReference;

    public ModelDatabase(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference();
    }

    public Task<Void> add(User user){
        //TODO there is problem to add '.' to path reference.
//        return databaseReference.child(user.getEmail()).setValue(user);
        return databaseReference.push().setValue(user);
    }

    public Task<Void> update(User user) {
        return databaseReference.child(user.getEmail()).updateChildren(user.toMap());
    }

    public Task<Void> remove(User user) {
        return databaseReference.child(user.getEmail()).removeValue();
    }
}
