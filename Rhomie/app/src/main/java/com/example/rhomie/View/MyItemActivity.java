package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rhomie.Controller.MyItemController;
import com.example.rhomie.Objects.Request;
import com.example.rhomie.R;

import java.util.ArrayList;

public class MyItemActivity extends AppCompatActivity {
    private MyItemController controller;
    private TextView addressText, checkInText, checkOutText, guestNumberText, flagsText;
    private String item_id;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item);

        controller = new MyItemController(this);

        listView = findViewById(R.id.requestListView);
        addressText = findViewById(R.id.address);
        checkInText = findViewById(R.id.checkIn);
        checkOutText = findViewById(R.id.checkOut);
        guestNumberText = findViewById(R.id.guestNumber);
        flagsText = findViewById(R.id.flags);

        Intent intent = this.getIntent();
        if(intent != null) {
            item_id = intent.getStringExtra("id");
            String address = intent.getStringExtra("address");
            String check_in = intent.getStringExtra("check_in");
            String check_out = intent.getStringExtra("check_out");
            String guest_number = intent.getStringExtra("guest_number");
            String flags = intent.getStringExtra("flags");

            addressText.setText(address);
            checkInText.setText(check_in);
            checkOutText.setText(check_out);
            guestNumberText.setText(guest_number);
            flagsText.setText(flags);
        }
        controller.getRequests(item_id);
    }

    public void drawRequests(ArrayList<Request> requestList) {
        ArrayList<String> requests = new ArrayList<>();
        for(Request req: requestList){
            requests.add(req.requestToString());
        }
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, requests);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MyItemActivity.this, ApprovalOrRejectionActivity.class);
                i.putExtra("req_id",requestList.get(position).getID());
                i.putExtra("item_id",requestList.get(position).getItemID());
                i.putExtra("full_name",requestList.get(position).getFullName());
                i.putExtra("phone_number",requestList.get(position).getPhoneNumber());
                i.putExtra("message",requestList.get(position).getMessage());
                startActivity(i);

            }
        });
    }
}