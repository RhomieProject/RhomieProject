package com.example.rhomie.Models;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Observable;

public class ApprovalOrRejectionModel extends Observable {

    private final String user;
    private DatabaseReference itemsDB,userToItemDB,userRequests,users;

    public ApprovalOrRejectionModel(){
        itemsDB = FirebaseDatabase.getInstance().getReference("items");
        userToItemDB= FirebaseDatabase.getInstance().getReference("UserToItem");
        userRequests = FirebaseDatabase.getInstance().getReference("UserRequests");
//        users = FirebaseDatabase.getInstance().getReference("users");
        user = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void ApproveRequest(String req, String item) {
        itemsDB.child(item).child("requests").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dts : snapshot.getChildren()){
                    Request request = dts.getValue(Request.class);
                    if(request.getID().equals(req)){
                        dts.getRef().child("status").setValue(1);
                        dts.getRef().getParent().getParent().child("isAvailable").setValue(false);
                        UpdateData(req,item,request.getFromUserID(),1, false);
                    }
                    else{
                        dts.getRef().child("status").setValue(-1);
                        UpdateData(req,item,request.getFromUserID(),-1,false);
                    }
                }
                setChanged();
                notifyObservers(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void UpdateData(String req, String item, String fromUserID,int status,boolean isAvailable) {
        userToItemDB.child(user).child(item).child("isAvailable").setValue(isAvailable);
        userRequests.child(fromUserID).child(item).child("status").setValue(status);
        userToItemDB.child(user).child(item).child("requests").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dts : snapshot.getChildren()){
                    Request request = dts.getValue(Request.class);
                    if(request.getID().equals(req))
                        dts.getRef().child("status").setValue(1);
                    else
                        dts.getRef().child("status").setValue(-1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void RejectRequest(String req, String item) {
        itemsDB.child(item).child("requests").child(req).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().child("status").setValue(-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
