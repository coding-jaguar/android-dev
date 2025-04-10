package com.shwet.simplecalculator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText number1, number2;
    Button addBtn, subtractBtn;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        addBtn = findViewById(R.id.addBtn);
        subtractBtn = findViewById(R.id.subtractBtn);
        resultText = findViewById(R.id.resultText);

        addBtn.setOnClickListener(v -> calculate('+'));
        subtractBtn.setOnClickListener(v -> calculate('-'));
    }

    void calculate(char op) {
        String n1 = number1.getText().toString().trim();
        String n2 = number2.getText().toString().trim();

        if (n1.isEmpty() || n2.isEmpty()) {
            Toast.makeText(this, "Enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double a = Double.parseDouble(n1);
            double b = Double.parseDouble(n2);
            double res = (op == '+') ? a + b : a - b;
            resultText.setText("Result: " + res);
        } catch (Exception e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }
}
