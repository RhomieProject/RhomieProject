package com.example.rhomie.Models;

import com.example.rhomie.Objects.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Observable;

public class RequestModel extends Observable {
    private DatabaseReference data;

    public RequestModel(){
        data =  FirebaseDatabase.getInstance().getReference("request");
    }


    public void addRequest(Request request){
        data.child(request.getFromUserID()).child(request.getItemID()).setValue(request)
        .addOnSuccessListener(suc->{
            setChanged();
            notifyObservers(true);
        }).addOnFailureListener(failed->{
            setChanged();
            notifyObservers(false);
        });
    }
}
