package com.example.rhomie.Models;

import com.example.rhomie.Objects.IUser;
import com.example.rhomie.View.ISingUpView;
import com.google.android.gms.tasks.Task;

public interface ISignUpModel {

    /**
     * init a database to work on it.
     */
    public void init();

    /**
     * this function returns if specific user exist in the database.
     * @param user
     * @return
     */
//    public void  isIn(IUser user);

    /**
     * adds the user to the authentication database
     *  if he is exist - will update all observes(SignUpController)
     *  otherwise      - adds the user to the realtime database.
     *
     * this function updates automatic the controller and call the Controller.update() function.
     * @param user
     */
    public void addUser(IUser user);

}
