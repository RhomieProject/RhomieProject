package com.example.rhomie.Controler;

import android.util.Log;

import com.example.rhomie.Models.ISignInModel;
import com.example.rhomie.Models.SignInModel;
import com.example.rhomie.Models.SignUpModel;
import com.example.rhomie.Objects.User;
import com.example.rhomie.View.ISignInView;

import java.util.Observable;
import java.util.Observer;

public class SignInController implements ISignInController, Observer {

    private ISignInView view;
    private ISignInModel model;

    public SignInController(ISignInView view) {
        this.view = view;
        model = new SignInModel();
        ((SignInModel)model).addObserver(this);
    }

    @Override
    public void OnSignIn(String email, String password) {
        User user = new User(email, password);
        int signInCode = user.isValid(email,password);

        if (signInCode == 0)
            Log.e("user", "problem in login"); //its mean code problem.

        if(signInCode == 1)
            view.signInError("Please enter username");

        if(signInCode == 2)
            view.signInError("Please enter password");

        if(signInCode ==3)
            view.signInError("Invalid email");

        if(signInCode == -1)
            model.CheckUser(user);
    }

    @Override
    public void update(Observable o, Object arg) {
        int keyCode = (int) arg;
        if(keyCode == -1){
            view.GoToHomePage();
            view.signInSuccess("Successfully logged in!");
        }
        else if (keyCode == 1){
            view.signInError("Email or password not correct!");
        }
    }
}