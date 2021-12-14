package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rhomie.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void goToMyAccount(View view) {
        Intent intent = new Intent(this, MyAccountActivity.class);
        startActivity(intent);
    }

    public void goToApartmentsList(View view) {
        Intent intent = new Intent(this, ApartmentListActivity.class);
        startActivity(intent);
    }
}