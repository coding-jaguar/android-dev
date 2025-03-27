package com.shwet.unit_converter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;

public class TemperatureFragment extends Fragment {
    EditText inputValue;
    Spinner fromUnit, toUnit;
    Button convertBtn;
    TextView outputValue;
    String[] units = {"Celsius", "Fahrenheit", "Kelvin"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);

        inputValue = view.findViewById(R.id.inputValue);
        fromUnit = view.findViewById(R.id.fromUnit);
        toUnit = view.findViewById(R.id.toUnit);
        convertBtn = view.findViewById(R.id.convertBtn);
        outputValue = view.findViewById(R.id.outputValue);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, units);
        fromUnit.setAdapter(adapter);
        toUnit.setAdapter(adapter);

        convertBtn.setOnClickListener(v -> convert());

        return view;
    }
    private void convert() {
        String inputStr = inputValue.getText().toString();
        if (inputStr.isEmpty()) {
            outputValue.setText("Please enter a value.");
            return;
        }

        double input = Double.parseDouble(inputStr);
        String from = fromUnit.getSelectedItem().toString();
        String to = toUnit.getSelectedItem().toString();
        double result;

        if (from.equals("Celsius") && to.equals("Fahrenheit")) {
            result = (input * 1.8) + 32;
        } else if (from.equals("Fahrenheit") && to.equals("Celsius")) {
            result = (input - 32) / 1.8;
        } else if (from.equals("Celsius") && to.equals("Kelvin")) {
            result = input + 273.15;
        } else if (from.equals("Kelvin") && to.equals("Celsius")) {
            result = input - 273.15;
        } else if (from.equals("Fahrenheit") && to.equals("Kelvin")) {
            result = (input - 32) / 1.8 + 273.15;
        } else if (from.equals("Kelvin") && to.equals("Fahrenheit")) {
            result = (input - 273.15) * 1.8 + 32;
        } else {
            result = input; // No conversion
        }

        outputValue.setText("Result: " + result + " " + to);
    }

}