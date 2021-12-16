package com.example.rhomie.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhomie.Controller.IMyAccountController;
import com.example.rhomie.Controller.MyAccountController;
import com.example.rhomie.Objects.IUser;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.User;
import com.example.rhomie.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAccountActivity extends AppCompatActivity implements IMyAccountView{

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;
    private Button logout;

    private ListView listView;
    private ArrayAdapter<Item> adapter;

    private IMyAccountController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        controller = new MyAccountController(this);
        controller.getItems();

        //-----------------------------
        listView = findViewById(R.id.listView);

//        items = new ArrayList<>();
//        items.add(new Item());

//        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        //------------------------------

        logout = findViewById(R.id.Logoutbutton);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.welcome);
        final TextView emailTextView = (TextView) findViewById(R.id.email);
        final TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
        final TextView phoneNumberTextView = (TextView) findViewById(R.id.phoneNumber);

            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                IUser userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String email = userProfile.getEmail();
                    String fullName = userProfile.getFullName();
                    String phoneNumber = userProfile.getPhoneNumber();

                    greetingTextView.setText("Welcome " + fullName + "!");
                    emailTextView.setText(email);
                    fullNameTextView.setText(fullName);
                    phoneNumberTextView.setText(phoneNumber);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyAccountActivity.this, "Something wrong happened!",Toast.LENGTH_LONG).show();
            }
        });

    }

    public void goToAddItem(View view) {
        startActivity(new Intent(MyAccountActivity.this, AddItemView.class));

    }

    public void Logout(View view){
        controller.Logout();
        startActivity(new Intent(MyAccountActivity.this, MainActivity.class));

    }

    @Override
    public void drawItems(ArrayList<Item> items) {
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

    }

    @Override
    public void getItemSuccess(String massage) {
        Toast.makeText(MyAccountActivity.this, massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getItemError(String massage) {
        Toast.makeText(MyAccountActivity.this, massage, Toast.LENGTH_SHORT).show();
    }
}