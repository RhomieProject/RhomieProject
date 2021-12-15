package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rhomie.Controler.AddItemController;
import com.example.rhomie.Objects.Address;
import com.example.rhomie.Objects.Flags;
import com.example.rhomie.R;

public class AddItemView extends AppCompatActivity {
    private EditText check_in, check_out,guest_number, city, street, street_number, floor, apartment_number;
    private CheckBox kosher, animals, accessibility, parking, smoking, wi_fi;
    private ProgressBar progressBar;
    private AddItemController controller;
    DatePickerDialog.OnDateSetListener dateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_view);

        controller = new AddItemController(this);

        check_in = findViewById(R.id.checkInEditText);
        check_out = findViewById(R.id.checkOutEditText);

        guest_number = findViewById(R.id.GuestNumberEditText);
        city = findViewById(R.id.CityEditText);
        street = findViewById(R.id.StreetEditText);
        street_number = findViewById(R.id.StreetNumberEditText);
        floor = findViewById(R.id.FloorEditText);
        apartment_number = findViewById(R.id.ApartmentNumberEditText);
        kosher = findViewById(R.id.kosherCheckBox);
        animals = findViewById(R.id.animalCheckBox);
        accessibility = findViewById(R.id.accessibilityCheckBox);
        parking = findViewById(R.id.parkinCheckBox);
        smoking = findViewById(R.id.smokingCheckBox);
        wi_fi = findViewById(R.id.WiFiCheckBox);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void ClickAddItem(View view){

        String CheckIn = check_in.getText().toString();
        String CheckOut = check_out.getText().toString();
        String GuestNumber = guest_number.getText().toString();

        String City = city.getText().toString();
        String Street = street.getText().toString();
        String StreetNumber = street_number.getText().toString();
        String Floor = floor.getText().toString();
        String ApartmentNumber = apartment_number.getText().toString();
        Address address = new Address(City, Street, StreetNumber , Floor, ApartmentNumber);

        boolean Kosher = kosher.isChecked();
        boolean Animals = animals.isChecked();
        boolean Accessibility = accessibility.isChecked();
        boolean Parking = parking.isChecked();
        boolean Smoking = smoking.isChecked();
        boolean WiFi = wi_fi.isChecked();
        Flags flags = new Flags(Kosher, Animals, Accessibility, Parking, Smoking, WiFi);

        progressBar.setVisibility(View.VISIBLE);

        controller.OnAddItem(address, flags, CheckIn,CheckOut,GuestNumber);
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