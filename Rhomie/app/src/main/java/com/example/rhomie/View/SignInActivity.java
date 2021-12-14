package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhomie.Controler.ISignInController;
import com.example.rhomie.Controler.SignInController;
import com.example.rhomie.R;

public class SignInActivity extends AppCompatActivity implements ISignInView {
    private EditText email, password;
    private ISignInController controllerSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        controllerSignIn = new SignInController(this);

        email = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.Password);
    }

    public void GoToHome(View view) {
        String emailS = email.getText().toString();
        String passwordS = password.getText().toString();
        controllerSignIn.OnSignIn(emailS, passwordS);
    }

    public void GoToHomePage(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    @Override
    public void signInSuccess(String massage) {
        Toast.makeText(SignInActivity.this, massage, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void signInError(String massage) {
        Toast.makeText(SignInActivity.this, massage, Toast.LENGTH_SHORT).show();
    }
}