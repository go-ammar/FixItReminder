package com.example.fixitreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Reminder extends AppCompatActivity {

    TextView mReminders;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        db = new Database(this);
        mReminders.setText(db.getData());
    }
}