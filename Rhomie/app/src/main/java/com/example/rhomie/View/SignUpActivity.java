package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhomie.Controler.ISingUpController;
import com.example.rhomie.Controler.SignUpConroller;
import com.example.rhomie.R;


public class SignUpActivity extends AppCompatActivity implements ISingUpView {
    private EditText first_name, last_name, id, phone_number, email, password;
    private ISingUpController controllerSingUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        controllerSingUp = new SignUpConroller(this);

        first_name = findViewById(R.id.edtxtFirstName);
        last_name = findViewById(R.id.edtxtLastName);
        id = findViewById(R.id.edtxtId);
        phone_number = findViewById(R.id.edtxtPhoneNumber);
        email = findViewById(R.id.edtxtEmail);
        password = findViewById(R.id.edtxtPassword);
    }

    public void sendClick(View view){
        String f_nameS = first_name.getText().toString();
        String l_nameS = last_name.getText().toString();
        String idS = id.getText().toString();
        String phone_numberS = phone_number.getText().toString();
        String emailS = email.getText().toString();
        String passwordS = password.getText().toString();

        //calls to the controller the dos the rest.
        controllerSingUp.OnSignUp(f_nameS,l_nameS,idS,phone_numberS,emailS,passwordS);
    }

    @Override
    public void singUpSuccess(String massage) {
        Toast.makeText(SignUpActivity.this, massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void singUpError(String massage) {
        Toast.makeText(SignUpActivity.this, massage, Toast.LENGTH_SHORT).show();
    }
}