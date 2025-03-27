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

public class WeightFragment extends Fragment {
    EditText inputValue;
    Spinner fromUnit, toUnit;
    Button convertBtn;
    TextView outputValue;
    String[] units = {"pound", "ounce", "ton", "kg", "g"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight, container, false);

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

        double inKG;
        if (from.equals("pound")) inKG = input * 0.453592;
        else if (from.equals("ounce")) inKG = input * 0.0283495;
        else if (from.equals("ton")) inKG = input * 907.185;
        else if (from.equals("kg")) inKG = input;
        else if (from.equals("g")) inKG = input * 0.001;
        else inKG = 0;

        double result;
        if (to.equals("pound")) result = inKG / 0.453592;
        else if (to.equals("ounce")) result = inKG / 0.0283495;
        else if (to.equals("ton")) result = inKG / 907.185;
        else if (to.equals("kg")) result = inKG;
        else if (to.equals("g")) result = inKG / 0.001;
        else result = 0;


        outputValue.setText("Result: " + result + " " + to);
    }
}
