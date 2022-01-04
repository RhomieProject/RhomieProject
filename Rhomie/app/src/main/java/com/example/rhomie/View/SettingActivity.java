package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rhomie.Controller.MyAccountController;
import com.example.rhomie.Controller.SettingController;
import com.example.rhomie.R;

public class SettingActivity extends AppCompatActivity {
    private TextView greetingTextView,emailTextView,fullNameTextView, phoneNumberTextView, idTextView;
    private String email,firstName,lastName,phoneNumber,id;
    private SettingController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        greetingTextView = findViewById(R.id.welcome);
        emailTextView = findViewById(R.id.email);
        fullNameTextView = findViewById(R.id.fullName);
        phoneNumberTextView = findViewById(R.id.phoneNumber);
        idTextView = findViewById(R.id.id);
        controller = new SettingController(this);

        controller.getDetails();



    }

    public void getDetails(String greeting,String email,String firstName,String lastName,String phoneNumber,String id){
        this.greetingTextView.setText(greeting);
        this.emailTextView.setText(email);
        this.fullNameTextView.setText(firstName+" "+lastName);
        this.phoneNumberTextView.setText(phoneNumber);
        this.idTextView.setText(id);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public void Logout(View view){
        controller.Logout();
        startActivity(new Intent(SettingActivity.this, MainActivity.class));
    }

    public void goToHomePage(View view) {
        startActivity(new Intent(SettingActivity.this, HomeActivity.class));
    }

    public void goToChangeInfo(View view) {
        Intent i = new Intent(SettingActivity.this, UpdateUserActivity.class);
        i.putExtra("email",email);
        i.putExtra("first_name",firstName);
        i.putExtra("last_name",lastName);
        i.putExtra("id",id);
        i.putExtra("phone_number",phoneNumber);
        startActivity(i);
    }

}