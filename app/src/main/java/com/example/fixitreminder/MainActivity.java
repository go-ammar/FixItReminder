package com.example.fixitreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText message;
    Button mButton;
    EditText mins;
    EditText hours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (EditText) findViewById(R.id.EditTextmessage);
        mButton = (Button) findViewById(R.id.saveButton);
        mins = (EditText) findViewById(R.id.editTextMins);
        hours = (EditText) findViewById(R.id.editTextHours);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO what happens when you click the save button? logic goes here.
            }
        });

    }
}