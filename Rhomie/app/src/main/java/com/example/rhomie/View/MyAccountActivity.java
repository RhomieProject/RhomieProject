package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private TextView greetingTextView,emailTextView,fullNameTextView, phoneNumberTextView;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private IMyAccountController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        listView = findViewById(R.id.requestListView);
        greetingTextView = findViewById(R.id.welcome);
        emailTextView = findViewById(R.id.email);
        fullNameTextView = findViewById(R.id.fullName);
        phoneNumberTextView = findViewById(R.id.phoneNumber);

        controller = new MyAccountController(this);
        controller.getItems();
        controller.getDetails();
    }

    @Override
    public void drawItems(ArrayList<Item> items) {

        ArrayList<String> itemsS = new ArrayList<>();
        for(Item item:items){
            itemsS.add(item.itemToString(true));
        }
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, itemsS);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setClickable(true);

//-------------------------------------------------------------------------------//

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MyAccountActivity.this, MyItemActivity.class);
                i.putExtra("id",items.get(position).getItem());
                i.putExtra("address",items.get(position).getAddress().addressToString());
                i.putExtra("check_in",items.get(position).getCheckIn());
                i.putExtra("check_out",items.get(position).getCheckOut());
                i.putExtra("guest_number",items.get(position).getGuestNumber());
                i.putExtra("flags",items.get(position).getFlags().flagsToString());
                startActivity(i);

            }
        });
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

    public void goToHomePage(View view) {
        startActivity(new Intent(MyAccountActivity.this, HomeActivity.class));
    }

}