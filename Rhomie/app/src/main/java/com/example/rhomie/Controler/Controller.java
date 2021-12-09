package com.example.rhomie.Controler;

import android.app.Activity;
import android.widget.EditText;

import com.example.rhomie.Models.IModelDatabase;
import com.example.rhomie.Models.ModelDatabase;
import com.example.rhomie.R;

public class Controller {

    private Activity activity;
    private ModelDatabase modelDatabase;

    public Controller(Activity activity){
        this.activity = activity;
        modelDatabase = new ModelDatabase();
    }

    public void checkDetails(String name,String Lname,String id,String phone,String email,String password){
        EditText d = activity.findViewById(R.id.edtxtPhoneNumber);
        d.setText("sdsd");
    }


}
