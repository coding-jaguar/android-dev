package com.shwet.quizapp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    TextView questionText;
    RadioGroup optionsGroup;
    RadioButton option1, option2, option3, option4;
    Button submitBtn;
    ProgressBar progressBar;

    String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'Romeo and Juliet'?",
            "What is the largest mammal?",
            "Which element has the chemical symbol 'O'?"
    };

    String[][] options = {
            {"Paris", "Rome", "Berlin", "Madrid"},
            {"Earth", "Mars", "Venus", "Jupiter"},
            {"William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain"},
            {"Elephant", "Blue Whale", "Giraffe", "Great White Shark"},
            {"Oxygen", "Gold", "Osmium", "Zinc"}
    };

    int[] correctAnswers = {0, 1, 0, 1, 0};

    int index = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submitBtn = findViewById(R.id.submitBtn);
        progressBar = findViewById(R.id.progressBar);

        loadQuestion();

        submitBtn.setOnClickListener(v -> {
            if (submitBtn.getText().equals("Submit")) {
                int selectedId = optionsGroup.getCheckedRadioButtonId();
                if (selectedId == -1) return;

                RadioButton selected = findViewById(selectedId);
                int selectedIndex = optionsGroup.indexOfChild(selected);

                for (int i = 0; i < optionsGroup.getChildCount(); i++) {
                    optionsGroup.getChildAt(i).setEnabled(false);
                }

                if (selectedIndex == correctAnswers[index]) {
                    selected.setBackgroundColor(Color.GREEN);
                    score++;
                } else {
                    selected.setBackgroundColor(Color.RED);
                    RadioButton correct = (RadioButton) optionsGroup.getChildAt(correctAnswers[index]);
                    correct.setBackgroundColor(Color.GREEN);
                }

                submitBtn.setText("Next");

            } else {
                index++;
                if (index < questions.length) {
                    loadQuestion();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("total", questions.length);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    void loadQuestion() {
        optionsGroup.clearCheck();
        for (int i = 0; i < optionsGroup.getChildCount(); i++) {
            optionsGroup.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
            optionsGroup.getChildAt(i).setEnabled(true);
        }
        questionText.setText(questions[index]);
        option1.setText(options[index][0]);
        option2.setText(options[index][1]);
        option3.setText(options[index][2]);
        option4.setText(options[index][3]);
        progressBar.setProgress((index + 1) * 100 / questions.length);
        submitBtn.setText("Submit");
    }
}
