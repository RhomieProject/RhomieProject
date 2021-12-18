package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rhomie.Controller.ApartmentListController;
import com.example.rhomie.Controller.IApartmentListController;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.R;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;

public class ApartmentListActivity extends AppCompatActivity implements IApartmentListView{
    ListView listView;
    ArrayAdapter<Item> adapter;
    private IApartmentListController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_list);
        controller = new ApartmentListController(this);
        listView = findViewById(R.id.listViewText);
        listView.setAdapter(adapter);
        controller.getItems();
    }

    @Override
    public void drawItems(ArrayList<Item> items) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    public void goToHomePage(View view) {
        startActivity(new Intent(ApartmentListActivity.this, HomeActivity.class));
    }
}



