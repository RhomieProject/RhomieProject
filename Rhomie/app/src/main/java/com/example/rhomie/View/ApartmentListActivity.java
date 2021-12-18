package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.example.rhomie.Controller.ApartmentListController;
import com.example.rhomie.Controller.IApartmentListController;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.databinding.ActivityApartmentListBinding;
import java.util.ArrayList;

public class ApartmentListActivity extends AppCompatActivity implements IApartmentListView{
    ActivityApartmentListBinding binding;
   // ListView listView;
    ArrayAdapter<String> adapter;
    private IApartmentListController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApartmentListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller = new ApartmentListController(this);
        controller.getItems();
    }

    @Override
    public void drawItems(ArrayList<Item> items) {
        ArrayList<String> itemsS = new ArrayList<>();
        for(Item item:items){
            itemsS.add(item.itemToString(false));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,itemsS);
        adapter.notifyDataSetChanged();
        binding.listViewText.setAdapter(adapter);
        binding.listViewText.setClickable(true);
        binding.listViewText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ApartmentListActivity.this,ItemActivity.class);
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



