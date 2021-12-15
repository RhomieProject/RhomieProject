package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rhomie.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void goToSignInPage(View view) {
        Intent intent1 = new Intent(this, SignInActivity.class);
        startActivity(intent1);
    }

    public void goToSignUpPage(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

}


//TODO sigh up - Authentication database.   done.


//TODO log in

//TODO update user

//TODO ADD ITEM - view page and intent from.
//


//TODO view items as a list