package com.example.fixitreminder;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    DatabaseHelper mHelper;
    Button mButtonshift;

    CountDownTimer mCountDownTimer;

    long totalDelay;
    long mStartTimeInMillis;
    long mtimeLeftInMillis;
    long mEndTime;

    Runnable runnable;
    Handler handler = new Handler();

    boolean isScreenOn;


    //call resetTimer every time isScreenOn is false
    //if isScreenOn is true then startTimer. abh yahan pe variables pass krne hai to different states


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper = new DatabaseHelper(this);

        message = findViewById(R.id.EditTextmessage);
        mButton = findViewById(R.id.saveButton);
        mins = findViewById(R.id.editTextMins);
        hours = findViewById(R.id.editTextHours);
        mButtonshift = findViewById(R.id.shift_button);

//        totalDelay = mHelper.getTimeData();

//        setTime(totalDelay);
//        startTimer();




//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle("textTitle")
//                .setContentText(mHelper.getCommentData())
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO what happens when you click the save button? logic goes here.

                mHelper.insertData(1000 * 60 * Integer.parseInt(mins.getText().toString()) +
                                (Integer.parseInt(hours.getText().toString()) * 60),
                        message.getText().toString().trim());

                mins.setText("");
                message.setText("");
                hours.setText("");

                mins.setHint(R.string.time_mins);
                hours.setHint(R.string.time_hrs);
                message.setHint(R.string.reminder_text);
            }
        });


        mButtonshift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Reminder.class);
                startActivity(intent);
                finishActivity(1);
            }
        });

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        isScreenOn = pm.isInteractive();



        if(isScreenOn){
          startTimer();
        }


//        handler.postDelayed(runnable = new Runnable() {
//            public void run() {
//                //do something
//                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//                isScreenOn = pm.isInteractive();
//                //           Log.d("humariApp", "onCreate after delay again and again: " + isScreenOn);
//                handler.postDelayed(runnable, totalDelay);
//            }
//        }, totalDelay);


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
                //              Log.d("humariApp", "onStop after delay again and again: " + isScreenOn);
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
                //               Log.d("humariApp", "onPause after delay again and again: " + isScreenOn);
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
                //             Log.d("humariApp", "onResume after delay again and again: " + isScreenOn);
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

    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mtimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mtimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mtimeLeftInMillis = millisUntilFinished;

            }
            @Override
            public void onFinish() {
        //        mTimerRunning = false;

            }
        }.start();
      //  mTimerRunning = true;
    }



    private void resetTimer() {
        mtimeLeftInMillis = mStartTimeInMillis;
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