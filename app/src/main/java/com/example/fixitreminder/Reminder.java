package com.example.fixitreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Reminder extends AppCompatActivity {

    TextView mReminders;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        Log.d("humariApp", "onCreate reminder activity: ");
        db = new DatabaseHelper(this);
        mReminders.setText(db.getData());
    }


}