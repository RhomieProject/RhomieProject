package com.example.rhomie.Controler;

import android.util.Log;
import com.example.rhomie.Models.ISignUpModel;
import com.example.rhomie.Models.SignUpModel;
import com.example.rhomie.Objects.User;
import com.example.rhomie.View.ISignUpView;

import java.util.Observable;
import java.util.Observer;

public class SignUpController implements ISignUpController, Observer {

    private ISignUpView view;
    private ISignUpModel model;

    public SignUpController(ISignUpView view) {
        this.view = view;
        model = new SignUpModel();

        ((SignUpModel)model).addObserver(this);
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
            view.signUpError("First name consists only letters");
        }
        if(signupCode == 2){
            view.signUpError("Last name consists only letters");
        }
        if(signupCode == 3){
            view.signUpError("ID consists only digits and should be equal to 9");
        }
        if(signupCode == 4){
            view.signUpError("Phone number consists only digits and should be equal to 10");
        }
        if(signupCode == 5){
            view.signUpError("This is not an email");
        }
        if(signupCode == 6){
            view.signUpError("Password must be up than 6");
        }
        if(signupCode == -1){

            //TODO change isIn method to addUser method.
            model.addUser(user);


        }
    }

    /** this function called automatic from SignUpModel.addUser()
     *  gets -1 if addUser success to add to authentication and realtime database
     *  gets 1 if addUser added to authentication but not to realtime database
     *  gets 2 if add user failed to add user to authentication database
     */
    @Override
    public void update(Observable o, Object arg) {
        int keyCode = (int) arg;
        if(keyCode == -1){
            view.signUpSuccess("Successfully signed up!");
        }else if(keyCode == 1){
            Log.e("firebase", "the user added to the authentication but not to realtime database");
        }else if (keyCode == 2){
            view.signUpError("Email already exists!");
        }

    }
}
