package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rhomie.Controler.AddItemController;
import com.example.rhomie.R;

public class AddItemView extends AppCompatActivity {
    private ProgressBar progressBar;
    private AddItemController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_view);

        controller = new AddItemController(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

    }

    public void ClickAddItem(View view){
        progressBar.setVisibility(View.VISIBLE);
        controller.OnAddItem();
    }




    public void AddItemSuccess(String massage){
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
    }

    public void AddItemError(String massage){
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(AddItemView.this, massage, Toast.LENGTH_SHORT).show();
    }


}