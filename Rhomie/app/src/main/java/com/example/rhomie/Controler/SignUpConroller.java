package com.example.rhomie.Controler;

import android.util.Log;

import com.example.rhomie.Models.ISignUpModel;
import com.example.rhomie.Models.SignUpModel;
import com.example.rhomie.Objects.IUser;
import com.example.rhomie.Objects.User;
import com.example.rhomie.View.ISingUpView;

public class SignUpConroller implements ISingUpController {

    private ISingUpView view;
    private ISignUpModel model;

    public SignUpConroller(ISingUpView view) {
        this.view = view;
        model = new SignUpModel();
    }

    @Override
    public void OnSignUp(String first_name, String last_name, String id, String phone_number, String email, String password) {
        User user = new User(email, password, first_name, last_name, phone_number, id);
        int signupCode = user.isValid();

        //TODO to write the reason its not correct for each massage
        if(signupCode == 0){
            Log.e("user","user cant be null"); //its mean code problem.
        }
        if(signupCode == 1){
            view.singUpError("is not correct first name");
        }
        if(signupCode == 2){
            view.singUpError("is not correct last name");
        }
        if(signupCode == 3){
            view.singUpError("is not correct id");
        }
        if(signupCode == 4){
            view.singUpError("is not correct phone number");
        }
        if(signupCode == 5){
            view.singUpError("is not correct email");
        }
        if(signupCode == 6){
            view.singUpError("is not correct password");
        }
        if(signupCode == -1){
            if(model.addUser(user)==false){
                Log.e(getClass().getName(),"the addUser() function on SignUpModel that should add user to the firebase failed");
            }
            view.singUpSuccess("success to sign up!");
        }
    }
}
