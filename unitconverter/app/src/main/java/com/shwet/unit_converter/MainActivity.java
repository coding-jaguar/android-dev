package com.shwet.unit_converter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    Spinner unitTypeSpinner;
    String[] unitTypes = {"Length", "Weight", "Temperature"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitTypeSpinner = findViewById(R.id.unitTypeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, unitTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitTypeSpinner.setAdapter(adapter);

        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Fragment selectedFragment;
                switch (pos) {
                    case 0: selectedFragment = new LengthFragment(); break;
                    case 1: selectedFragment = new WeightFragment(); break;
                    case 2: selectedFragment = new TemperatureFragment(); break;
                    default: selectedFragment = new LengthFragment();
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, selectedFragment)
                        .commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}