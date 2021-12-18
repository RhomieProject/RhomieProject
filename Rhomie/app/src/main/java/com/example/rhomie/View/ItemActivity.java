package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.rhomie.R;
import com.example.rhomie.databinding.ActivityItemBinding;

public class ItemActivity extends AppCompatActivity {
    ActivityItemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        binding = ActivityItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        if(intent != null){
            String city = intent.getStringExtra("city");
            String check_in = intent.getStringExtra("check_in");
            String check_out = intent.getStringExtra("check_out");
            String guest_number = intent.getStringExtra("guest_number");
            String flags = intent.getStringExtra("flags");

            binding.city.setText(city);
            binding.checkIn.setText(check_in);
            binding.checkOut.setText(check_out);
            binding.guestNumber.setText(guest_number);
            binding.flags.setText(flags);
        }
    }
}