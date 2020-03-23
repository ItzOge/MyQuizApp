package com.example.android.simplequizapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mQuestions,mScore;
    Button mQuitButton,mNextButton;
    RadioGroup mRadioGroup;
    RadioButton mRadioButton1;
    RadioButton mRadioButton2;
    RadioButton mRadioButton3;
    RadioButton mRadioButton4;
    TextView mText;


    public static int correctAnwers = 0, incorrectAnswers = 0, marks = 0;
    public int flag = 0;

    String questions[] = {
            "Which method can be defined only once in a program?",
            "The following are characteristics of objects EXCEPT?",
            "Which keyword is used by method to refer to the object that invoked it?",
            "The main concepts of OOPs in java are all EXCEPT?",
            "Which of these access specifiers can be used for an interface?",
            "What is the special keyword that is used an access modifier in java?",
            "What is the return type of Constructors?",
            "The built-in class loaders are all EXCEPT?",
            "Which of these method of class String is used to compare two String objects for their equality?",
            "The different types of inheritance in Java are all EXCEPT?"
    };

    String answers[]= {"main method","Composition","this","Monomorphism","public","final","None of the mentioned","Abstraction","equals()","Multiple"};

    String options[]={"finalize method","main method","static method","private method",
            "State","Behaviour","Identity","Composition",
            "import","this","catch","abstract",
            "Monomorphism","Abstraction","Encapsulation","Inheritance",
            "public","protected","private","All of the mentioned",
            "this","final","static","void",
            "int","float","void","None of the mentioned",
            "Bootstrap","Abstraction","System/Application","Extension",
            "equals()","Equals()","isequal()","Isequal()",
            "Single","Multilevel","Hybrid","Multiple"};

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        TextView textView=(TextView)findViewById(R.id.textView3);
        Intent intent = getIntent();
         name= intent.getStringExtra("username");

        if (name.trim().equals(""))
            textView.setText("Your score");
        else
            textView.setText( name + "'s score");

        mQuestions = findViewById(R.id.questions);
        mScore = findViewById(R.id.score);
        mQuitButton = findViewById(R.id.quitButton);
        mNextButton = findViewById(R.id.nextButton);
        mRadioButton1 = findViewById(R.id.radioButton1);
        mRadioButton2 = findViewById(R.id.radioButton2);
        mRadioButton3 = findViewById(R.id.radioButton3);
        mRadioButton4 = findViewById(R.id.radioButton4);
        mRadioGroup = findViewById(R.id.radioGroup);

        mQuitButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);

        mQuestions.setText(questions[flag]);
        mRadioButton1.setText(options[0]);
        mRadioButton2.setText(options[1]);
        mRadioButton3.setText(options[2]);
        mRadioButton4.setText(options[3]);




    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.nextButton:
                //check to see if user made a selection
                if(mRadioGroup.getCheckedRadioButtonId()== -1){
                    Toast.makeText(this, "Please make a selection",Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton userChoice = (RadioButton)findViewById(mRadioGroup.getCheckedRadioButtonId());
                String userChoiceText = userChoice.getText().toString();

                if(userChoiceText == answers[flag]){
                    correctAnwers++;
                    Toast.makeText(this,"That's correct",Toast.LENGTH_SHORT).show();
                }
                else {
                    incorrectAnswers++;
                    Toast.makeText(this,"Oops! Incorrect answer",Toast.LENGTH_SHORT).show();
                }

                flag++;

                if(mScore != null){
                    mScore.setText(" "+ correctAnwers);

                    //checks to see if question ahs been exhausted
                    if(flag<questions.length){
                        mQuestions.setText(questions[flag]);
                        mRadioButton1.setText(options[flag*4]);
                        mRadioButton2.setText(options[flag*4 + 1]);
                        mRadioButton3.setText(options[flag*4 + 2]);
                        mRadioButton4.setText(options[flag*4 + 3]);
                    } else {
                        marks = correctAnwers;
                        Intent mIntent = new Intent(getApplicationContext(),ResultActivity.class);
                        startActivity(mIntent);
                    }

                    mRadioGroup.clearCheck();
                }




                break;

            case R.id.quitButton:
                String quitMessage = "I am so sad you had to quit";
                Intent nIntent = new Intent(getApplicationContext(), ResultActivity.class);
                nIntent.putExtra("quitMessage",quitMessage);
                startActivity(nIntent);
                finish();
                break;

            default:
                break;
        }

    }

    private void sendMail() {
        String emailAddress = "okwuchukwu.chukwu@gmail.com";
        String[] recipients = emailAddress.split(",");
        String correct = String.valueOf(correctAnwers);
        String incorrect = String.valueOf(incorrectAnswers);
        String subject = "Result from test app user";
        String message = "User: "+ name + "\n"
                        + "Correct answers: "
                        + correct + "\n"
                        + "Incorrect answers: "
                        + incorrect;




        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "message");
        intent.setData(Uri.parse("mailto:okwuchukwu.chukwu@gmail.com")); // or just "mailto:" for blank
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        startActivity(intent);
        Toast.makeText(getApplication(),"Email sent",Toast.LENGTH_SHORT).show();


    }
}
