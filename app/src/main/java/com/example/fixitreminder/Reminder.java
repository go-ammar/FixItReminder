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

        mReminders = findViewById(R.id.textView);

        Log.d("humariApp", "onCreate reminder activity: ");
        try  {
            DatabaseHelper databaseHelper = db = new DatabaseHelper(this);
        }
        catch (Exception e){
            Log.d("HumariApp", "onCreate reminder "+ db.getData());
        }
        mReminders.setText(db.getData());
    }

}