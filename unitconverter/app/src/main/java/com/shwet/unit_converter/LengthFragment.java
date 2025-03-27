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

public class LengthFragment extends Fragment {
    EditText inputValue;
    Spinner fromUnit, toUnit;
    Button convertBtn;
    TextView outputValue;
    String[] units = {"inch", "foot", "yard", "mile", "cm", "km"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_length, container, false);

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

        double inCM;
        if (from.equals("inch")) inCM = input * 2.54;
        else if (from.equals("foot")) inCM = input * 30.48;
        else if (from.equals("yard")) inCM = input * 91.44;
        else if (from.equals("mile")) inCM = input * 160934;
        else if (from.equals("cm")) inCM = input;
        else if (from.equals("km")) inCM = input * 100000;
        else inCM = 0;

        double result;
        if (to.equals("inch")) result = inCM / 2.54;
        else if (to.equals("foot")) result = inCM / 30.48;
        else if (to.equals("yard")) result = inCM / 91.44;
        else if (to.equals("mile")) result = inCM / 160934;
        else if (to.equals("cm")) result = inCM;
        else if (to.equals("km")) result = inCM / 100000;
        else result = 0;


        outputValue.setText("Result: " + result + " " + to);
    }
}