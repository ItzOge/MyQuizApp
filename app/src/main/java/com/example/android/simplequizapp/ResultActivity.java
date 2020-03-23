package com.example.android.simplequizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    Button mReplay,mSubmit;
    TextView totalCorrect;
    TextView totalIncorrect;
    TextView mQuitMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        totalCorrect = findViewById(R.id.correctAnswers);
        totalIncorrect = findViewById(R.id.incorrectAnswers);
        mReplay = findViewById(R.id.replay);
        mQuitMessage = findViewById(R.id.quitMessage);
        mSubmit = findViewById(R.id.submitButton);

        mReplay.setOnClickListener(this);
        mSubmit.setOnClickListener(this);

        Intent intent = getIntent();
        String quit= intent.getStringExtra("quitMessage");
        mQuitMessage.setText(quit);

        StringBuffer sb = new StringBuffer();
        sb.append("Total Correct answers: "+ QuestionActivity.correctAnwers + "\n");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("Total Incorrect answers: "+ QuestionActivity.incorrectAnswers + "\n");

        totalCorrect.setText(sb);
        totalIncorrect.setText(sb2);

//        QuestionActivity.correctAnwers = 0;
//        QuestionActivity.incorrectAnswers = 0;


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.replay:
                Intent newInt = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(newInt);
                finish();
                break;
            case R.id.submitButton:
                String recipient = "okwuchukwu.chukwu@gmail.com";
                String[] recipientList = recipient.split(",");
                String correct = String.valueOf(QuestionActivity.correctAnwers);
                String incorrect = String.valueOf(QuestionActivity.incorrectAnswers);
                String subject = "Result from test app user";
                String message = "User: "+  "\n"
                        + "Correct answers: "
                        + QuestionActivity.correctAnwers + "\n"
                        + "Incorrect answers: "
                        + QuestionActivity.incorrectAnswers;




                Intent intent = new Intent(Intent.ACTION_SEND); // it's not ACTION_SEN
                intent.putExtra(Intent.EXTRA_EMAIL, recipientList);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                intent.setType("message/rfc822"); // or just "mailto:" for blank
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                startActivity(intent.createChooser(intent,"Choose application"));

                break;
            default:
                break;
        }

    }
}
