package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.rhomie.Controller.ISignUpController;
import com.example.rhomie.Controller.SignUpController;
import com.example.rhomie.R;

public class SignUpActivity extends AppCompatActivity implements ISignUpView {
    private EditText first_name, last_name, id, phone_number, email, password;
    private ISignUpController controllerSignUp;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        controllerSignUp = new SignUpController(this);

        first_name = findViewById(R.id.edtxtFirstName);
        last_name = findViewById(R.id.edtxtLastName);
        id = findViewById(R.id.edtxtId);
        phone_number = findViewById(R.id.edtxtPhoneNumber);
        email = findViewById(R.id.edtxtEmail);
        password = findViewById(R.id.edtxtPassword);

        progressBar = findViewById(R.id.progressBar);
    }

    public void sendClick(View view){
        String f_nameS = first_name.getText().toString();
        String l_nameS = last_name.getText().toString();
        String idS = id.getText().toString();
        String phone_numberS = phone_number.getText().toString();
        String emailS = email.getText().toString();
        String passwordS = password.getText().toString();

        progressBar.setVisibility(View.VISIBLE);

        //calls to the controller the dos the rest.
        controllerSignUp.OnSignUp(f_nameS,l_nameS,idS,phone_numberS,emailS,passwordS);
    }

    @Override
    public void signUpSuccess(String massage) {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(SignUpActivity.this, massage, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));//TODO
    }

    @Override
    public void signUpError(String massage) {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(SignUpActivity.this, massage, Toast.LENGTH_SHORT).show();
    }
}