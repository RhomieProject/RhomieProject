package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rhomie.Controller.ApartmentListController;
import com.example.rhomie.Controller.IApartmentListController;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.R;

import java.io.Serializable;
import java.util.ArrayList;

public class ApartmentListActivity extends AppCompatActivity implements IApartmentListView{
    ArrayAdapter<String> adapter;
    private IApartmentListController controller;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_list);
        controller = new ApartmentListController(this);
        controller.getItems();
        listView = findViewById(R.id.listViewText);

    }

    @Override
    public void drawItems(ArrayList<Item> items) {
        ArrayList<String> itemsS = new ArrayList<>();
        for(Item item:items){
            itemsS.add(item.itemToString(false));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,itemsS);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ApartmentListActivity.this, RequestActivity.class);
                i.putExtra("item_id", items.get(position).getItem());
                i.putExtra("user_id", items.get(position).getFatherID());
                i.putExtra("city",items.get(position).getAddress().getCity());
                i.putExtra("check_in",items.get(position).getCheckIn());
                i.putExtra("check_out",items.get(position).getCheckOut());
                i.putExtra("guest_number",items.get(position).getGuestNumber());
                i.putExtra("flags",items.get(position).getFlags().flagsToString());
                startActivity(i);
            }
        });

    }

    public void goToHomePage(View view) {
        startActivity(new Intent(ApartmentListActivity.this, HomeActivity.class));
    }
}



