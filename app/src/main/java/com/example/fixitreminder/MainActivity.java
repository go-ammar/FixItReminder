package com.example.fixitreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText message;
    Button mButton;
    EditText mins;
    EditText hours;
    int totalDelay;

    Runnable runnable;
    Handler handler = new Handler();

    boolean isScreenOn;


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

                totalDelay = 1000 * 60 * Integer.parseInt(mins.getText().toString()) + (Integer.parseInt(hours.getText().toString()) * 60);
                String reminderText = message.getText().toString();



            }
        });

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        isScreenOn = pm.isInteractive();

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //do something
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                isScreenOn = pm.isInteractive();
                Log.d("humariApp", "onCreate after delay again and again: " + isScreenOn);
                handler.postDelayed(runnable, totalDelay);
            }
        }, totalDelay);


//        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        isScreenOn = pm.isInteractive();
//
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                //TODO your background code
//                Log.d("humariApp", "onCreate " + isScreenOn);
//
//            }
//        });


    }

    @Override
    protected void onStop() {
        super.onStop();

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        isScreenOn = pm.isInteractive();

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //do something
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                isScreenOn = pm.isInteractive();
                Log.d("humariApp", "onStop after delay again and again: " + isScreenOn);
                handler.postDelayed(runnable, totalDelay);
            }
        }, totalDelay);

//        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        isScreenOn = pm.isInteractive();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//                isScreenOn = pm.isInteractive();
//                //Write your code here
//                Log.d("humariApp", "onStop with delay " + isScreenOn);
//            }
//        }, 5000);
//
//
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                //TODO your background code
//                Log.d("humariApp", "onStop " + isScreenOn);
//
//            }
//        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        isScreenOn = pm.isInteractive();

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //do something
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                isScreenOn = pm.isInteractive();
                Log.d("humariApp", "onPause after delay again and again: " + isScreenOn);
                handler.postDelayed(runnable, totalDelay);
            }
        }, totalDelay);


//        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        isScreenOn = pm.isInteractive();
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                //TODO your background code
//                Log.d("humariApp", "onPause " + isScreenOn);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        isScreenOn = pm.isInteractive();

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //do something
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                isScreenOn = pm.isInteractive();
                Log.d("humariApp", "onResume after delay again and again: " + isScreenOn);
                handler.postDelayed(runnable, totalDelay);
            }
        }, totalDelay);


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//                isScreenOn = pm.isInteractive();
//                //Write your code here
//                Log.d("humariApp", "onResume with delay " + isScreenOn);
//            }
//        }, 5000);
//
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                //TODO your background code
//                Log.d("humariApp", "onResume " + isScreenOn);
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        Log.d("humariApp", "onDestroy: ");


    }


}


/*

AsyncTask.execute(new Runnable() {
   @Override
   public void run() {
      //TODO your background code
   }
});

    boolean checkIfScreenON(){

    }

    void Timer(int mins, int hours){
            int curmins;
            int curhours;
            if (!checkIfScreenOn()){
                curmins=0;
                curhours=0;
            }else {
            timer start hogaya yahan pe hpor both mins and hours.
            jab timer = mins, hours show popup.
            }

    }

    void Timer(){

    }



    void main(){


    for(execute()==true){
        void timer();
    }
    }

 */
