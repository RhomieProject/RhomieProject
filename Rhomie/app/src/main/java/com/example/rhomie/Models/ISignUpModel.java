package com.example.rhomie.Models;

import com.example.rhomie.Objects.IUser;
import com.google.android.gms.tasks.Task;

public interface ISignUpModel {

    /**
     * init a database to work on it.
     */
    public void init();

    /**
     * adds the user to the database
     * if success returns true otherwise returns false.
     * @param user
     */
    public boolean addUser(IUser user);

}
