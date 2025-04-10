package com.shwet.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText nameInput;
    Button startQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        startQuiz = findViewById(R.id.startQuiz);

        SharedPreferences prefs = getSharedPreferences("quizPrefs", MODE_PRIVATE);
        String savedName = prefs.getString("username", "");
        nameInput.setText(savedName);

        startQuiz.setOnClickListener(v -> {
            String username = nameInput.getText().toString().trim();
            if (!username.isEmpty()) {
                prefs.edit().putString("username", username).apply();
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
