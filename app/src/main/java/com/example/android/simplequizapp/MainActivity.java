package com.example.android.simplequizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     EditText nameEdit;
    Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEdit = (EditText)findViewById(R.id.name);
        mStartButton = findViewById(R.id.startButton);

        mStartButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String name = nameEdit.getText().toString();
               Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
               intent.putExtra("username",name);
               startActivity(intent);
               Toast.makeText(MainActivity.this,"Welcome "+ name, Toast.LENGTH_SHORT).show();
               finish();
           }
       });
    }
}
