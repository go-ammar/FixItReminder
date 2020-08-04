package com.example.fixitreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Reminder extends AppCompatActivity {

    TextView mReminders;
    DatabaseHelper db;
    ListView mListView;

    private String [] reminders = {"abs","123"};
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        mReminders = findViewById(R.id.textView);
        mListView = findViewById(R.id.ListView);
        mReminders.setMovementMethod(new ScrollingMovementMethod());



        Log.d("humariApp", "onCreate reminder activity: " + this );
        try  {
            DatabaseHelper databaseHelper = db = new DatabaseHelper(this);
        }
        catch (Exception e){
            Log.d("HumariApp", "onCreate reminder ");
        }
        mReminders.setText(db.getCommentData());
        reminders = db.getDataString();

        Log.d("humariApp", "string thingy " + db.getDataString().toString().trim());

        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, reminders);

        if (mListView !=null){
            mListView.setAdapter(mAdapter);
        }


    }



}