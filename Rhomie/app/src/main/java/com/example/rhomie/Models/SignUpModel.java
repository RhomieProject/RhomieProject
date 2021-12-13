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
    public void addUser(IUser user) {

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnSuccessListener(suc->{
                    databaseReference.child(mAuth.getUid()).setValue(user)
                            .addOnSuccessListener(suc2->{
                                //success to add user to authentication database and realtime database.users.
                                setChanged();
                                notifyObservers(-1);

                            }).addOnFailureListener(fail->{
                                //success to add user to authentication database but not to the realtime database.users.
                                setChanged();
                                notifyObservers(1);
                            });

                }).addOnFailureListener(fail->{
                    // failed to add user to authentication database - probably he is exist already.
                    setChanged();
                    notifyObservers(2);
                });

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


