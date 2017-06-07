package com.example.bsol.thequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.empty;
import static com.example.bsol.thequiz.R.string.Q1;

public class MainActivity extends AppCompatActivity {
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit(View v) {
        score = 0;
        // the next lines check if the question is right and tallies the score
        isCorrectRadioGroup(1, R.id.radio_Group_Q1, R.string.Q1A2);
        isCorrectRadioGroup(2, R.id.radio_Group_Q2, R.string.Q2A2);
        isCorrectRadioGroup(3, R.id.radio_Group_Q3, R.string.Q3A3);
        isCorrectEditText(4, R.id.name_field, R.string.Q4A);
        isCorrectQ5(); // I forgot to add a question with checkboxes so went back and haphazardly added this function
        score = score * 100 / 5;
        Toast.makeText(this, "Score: " + score + "%", Toast.LENGTH_SHORT).show();
    }

    // @questionNumber the number of the question ex: 1, 2, 3
    // @resource is the id of the RadioGroup ex: R.id.radio_Group_Q1
    // @correct answer is the id of the correct answer ex: R.string.Q1A2
    public boolean isCorrectRadioGroup(int questionNumber, int radioGroupId, int correctAnswerId) {
        RadioGroup radioGroupQ1 = (RadioGroup) findViewById(radioGroupId);
        int selectedId = radioGroupQ1.getCheckedRadioButtonId();
        if (selectedId != -1) { // this checks to make sure one of the radio buttons was pressed
            RadioButton radioButtonQ1 = (RadioButton) findViewById(selectedId);
            String answerQ1 = radioButtonQ1.getText().toString();
            if (answerQ1 == getString(correctAnswerId)) {
                Log.i("main", "Question " + questionNumber + " is correct!");
                score++;
                return true;
            } else {
                Log.i("main", "Question " + questionNumber + " is wrong!");
                return false;
            }
        }
        Log.i("main", "Question " + questionNumber + " is empty!");
        return false;
    }

    public boolean isCorrectEditText(int questionNumber, int editTextId, int correctAnswerId) {
        TextView editText = (TextView) findViewById(editTextId);
        String answerField = editText.getText().toString();
        if (answerField.equals("")) {
            Log.i("main", "Question " + questionNumber + " is empty!");
            return false;
        }
        if (answerField.equalsIgnoreCase(getString(correctAnswerId))) {
            Log.i("main", "Question " + questionNumber + " is correct!");
            score++;
            return true;
        }
        Log.i("main", "Question " + questionNumber + " is wrong!");
        return false;
    }

    public void isCorrectQ5(){
        CheckBox A1 = (CheckBox) findViewById(R.id.checkbox_Q5A1);
        CheckBox A2 = (CheckBox) findViewById(R.id.checkbox_Q5A2);
        CheckBox A3 = (CheckBox) findViewById(R.id.checkbox_Q5A3);
        if (A1.isChecked()&A2.isChecked()) {
            if(!A3.isChecked()){
            score++;
            Log.i("main", "Question 5 is correct!");
            return;}
        }
        Log.i("main", "Question 5 is wrong!");
    }

}
