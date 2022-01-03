package com.example.rhomie.Models;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.Request;
import com.example.rhomie.Objects.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Observable;

public class RequestModel extends Observable {
    private DatabaseReference itemsDB,userToItemDB,userRequests,users;
    public RequestModel(){
        itemsDB = FirebaseDatabase.getInstance().getReference("items");
        userToItemDB= FirebaseDatabase.getInstance().getReference("UserToItem");
        userRequests = FirebaseDatabase.getInstance().getReference("UserRequests");
        users = FirebaseDatabase.getInstance().getReference("users");

    }

    public void addRequest(String item_id, Request request) {
        DatabaseReference pushToItem = itemsDB.child(item_id).child("requests").push();
        String reqKey = pushToItem.getKey();
        request.setID(reqKey);
        itemsDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dts: snapshot.getChildren()){
                    Item item = dts.getValue(Item.class);
                    if(item.getItem().equals(item_id)) {
                        pushToItem.setValue(request).
                            addOnSuccessListener(suc->{
                                userToItemDB.child(item.getFatherID()).child(item_id).child("requests").child(reqKey).setValue(request);
                                addUserToItem(item,request);
                             //   item.addRequest(request);
                                setChanged();
                                notifyObservers(true);
                            }).addOnFailureListener(failed->{
                                setChanged();
                                notifyObservers(false);
                            });
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void addUserToItem(Item item, Request request){
        String user = getUser();
        String details = item.getAddress().getCity()+'#'+item.getGuestNumber()+'#'+item.getCheckIn()+" - "+item.getCheckOut()+'#'+item.getAddress().addressToString()+'#';
        userRequests.child(user).child(item.getItem()).setValue(request);
        userRequests.child(user).child(item.getItem()).child("details").setValue(details);

    }

    public String getUser(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void getDetails(String item, String message) {

        users.child(getUser()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> details = new ArrayList<>();
                User user = snapshot.getValue(User.class);
                String fullName = user.getFullName();
                String phoneNumber = user.getPhoneNumber();
                details.add(item);
                details.add(message);
                details.add(fullName);
                details.add(phoneNumber);
                setChanged();
                notifyObservers(details);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
