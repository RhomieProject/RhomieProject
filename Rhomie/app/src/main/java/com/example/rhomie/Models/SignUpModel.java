package com.example.rhomie.Models;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.IUser;
import com.example.rhomie.View.ISingUpView;
import com.example.rhomie.View.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

public class SignUpModel extends Observable implements ISignUpModel {
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;


    public SignUpModel() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        this.databaseReference = db.getReference("users");

        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void init() {
    //TODO if we need that
    }

    @Override
    public void isIn(IUser user) {
    //TODO check if user exist in Authentication database.

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnSuccessListener(suc->{
                    databaseReference.child(mAuth.getUid()).setValue(user)
                      .addOnSuccessListener(suc2->{
                            setChanged();
                            notifyObservers(true);

                    }).addOnFailureListener(fail->{
                        setChanged();
                        notifyObservers(false);
                    });

                }).addOnFailureListener(fail->{
                    setChanged();
                    notifyObservers(false);
        });
    }

    @Override
    public void addUser(IUser user) {



        /*
        AtomicBoolean success = new AtomicBoolean(false);
        databaseReference.child(user.getID()).setValue(user).addOnSuccessListener(suc->{
            success.set(true);
        }).addOnFailureListener(fail->{
            success.set(false);
        });
        return success.get();
         */
    }
}


