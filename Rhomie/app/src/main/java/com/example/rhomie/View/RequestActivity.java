package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rhomie.Controller.ApartmentListController;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.R;
import com.example.rhomie.databinding.ActivityItemBinding;

public class RequestActivity extends AppCompatActivity implements IRequestView {
    //private ActivityItemBinding binding;
    private ApartmentListController controller;
    private TextView cityText, checkInText, checkOutText, guestNumberText, flagsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        //binding = ActivityItemBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());


        cityText = findViewById(R.id.city);
        checkInText = findViewById(R.id.checkIn);
        checkOutText = findViewById(R.id.checkOut);
        guestNumberText = findViewById(R.id.guestNumber);
        flagsText = findViewById(R.id.flags);

        Intent intent = this.getIntent();
        if(intent != null){
            String city = intent.getStringExtra("city");
            String check_in = intent.getStringExtra("check_in");
            String check_out = intent.getStringExtra("check_out");
            String guest_number = intent.getStringExtra("guest_number");
            String flags = intent.getStringExtra("flags");


            cityText.setText(city);
            checkInText.setText(check_in);
            checkOutText.setText(check_out);
            guestNumberText.setText(guest_number);
            flagsText.setText(flags);
        }
    }

    public void askForItem(View view) {
//        addRequest();
    }

    @Override
    public void addRequest(Item item) {

    }

    @Override
    public void OnSuccess(String massage) {

    }

    @Override
    public void OnError(String massage) {

    }
}