package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rhomie.Controller.IMyAccountController;
import com.example.rhomie.Controller.MyAccountController;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.R;
import java.util.ArrayList;

public class MyAccountActivity extends AppCompatActivity implements IMyAccountView{
    public TextView greetingTextView,emailTextView,fullNameTextView, phoneNumberTextView;
    private ListView listView;
    private ArrayAdapter<Item> adapter;
    private IMyAccountController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        controller = new MyAccountController(this);
        controller.getItems();

        //-----------------------------
        listView = findViewById(R.id.listView);

//        items = new ArrayList<>();
//        items.add(new Item());

//        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        //------------------------------

        greetingTextView = findViewById(R.id.welcome);
        emailTextView = findViewById(R.id.email);
        fullNameTextView = findViewById(R.id.fullName);
        phoneNumberTextView = findViewById(R.id.phoneNumber);
        controller.getDetails();
    }

    @Override
    public void drawItems(ArrayList<Item> items) {
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
    }

    @Override
    public void getDetails(String greeting,String email,String fullName,String phoneNumber){
        this.greetingTextView.setText(greeting);
        this.emailTextView.setText(email);
        this.fullNameTextView.setText(fullName);
        this.phoneNumberTextView.setText(phoneNumber);
    }

    @Override
    public void getItemSuccess(String massage) {
        Toast.makeText(MyAccountActivity.this, massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getItemError(String massage) {
        Toast.makeText(MyAccountActivity.this, massage, Toast.LENGTH_SHORT).show();
    }

    public void Logout(View view){
        controller.Logout();
        startActivity(new Intent(MyAccountActivity.this, MainActivity.class));
    }

    public void goToAddItem(View view) {
        startActivity(new Intent(MyAccountActivity.this, AddItemView.class));
    }
}