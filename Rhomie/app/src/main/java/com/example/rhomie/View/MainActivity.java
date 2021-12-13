package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.rhomie.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}


//TODO sigh up - Authentication database
//

//TODO log in

//TODO update user

//TODO ADD ITEM all processes

//TODO view items as a list