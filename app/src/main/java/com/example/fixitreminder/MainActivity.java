package com.example.fixitreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


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
    long mtimeLeftInMillis;
    long mEndTime;

    Runnable runnable;
    Handler handler = new Handler();

    boolean isScreenOn;
    boolean mScreenOn =false;
    boolean timerRunning=false;

    Context mContext;


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

        totalDelay = 10000;
//        totalDelay = mHelper.getTimeData();


//        setTime(totalDelay);
//        startTimer();



        //TODO this is how to show notification

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




        final Context context = this;
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //do something
                if(!mScreenOn) {
                    startTimer(totalDelay, context);
                }

                //           Log.d("humariApp", "onCreate after delay again and again: " + isScreenOn);
                handler.postDelayed(runnable, 1000);
            }
        }, 1000);


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


        final Context context = this;
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //do something
                if(!mScreenOn) {
                    mCountDownTimer.cancel();

                    startTimer(totalDelay, context);
                }

                //           Log.d("humariApp", "onCreate after delay again and again: " + isScreenOn);
                handler.postDelayed(runnable, 1000);
            }
        }, 1000);

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

        final Context context = this;
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //do something
                if(!mScreenOn) {
                    mCountDownTimer.cancel();
                    startTimer(totalDelay, context);
                }

                //           Log.d("humariApp", "onCreate after delay again and again: " + isScreenOn);
                handler.postDelayed(runnable, 1000);
            }
        }, 1000);


    }

    @Override
    protected void onResume() {
        super.onResume();



        final Context context = this;
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //do something
                if(!mScreenOn) {
                    mCountDownTimer.cancel();
                    startTimer(totalDelay, context);
                }

                //           Log.d("humariApp", "onCreate after delay again and again: " + isScreenOn);
                handler.postDelayed(runnable, 1000);
            }
        }, 1000);

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


// long variable which keeps track of time spent shuru mae hoga 0.
// time 10000 apne guzaar liye 6000 secs
// remaining time should be 4000 secs.
// 10000 - 6000.
//10000 was totalTime and 6000 was time spent
// inititally it was 10000-0;
// what was the 0??? the long variable.




    private void startTimer(long totalTime, final Context context) {
        timerRunning = true;
        mScreenOn = true;
        mEndTime = System.currentTimeMillis() + mtimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(totalTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mtimeLeftInMillis = millisUntilFinished;

                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                isScreenOn = pm.isInteractive();
                Log.d("HumariApp", "onTick: " + millisUntilFinished );
                if(!isScreenOn){
                    //resetTimer();
                    mScreenOn = false;
                    timerRunning = false;
                    return;
                }

            }
            @Override
            public void onFinish() {
                //        mTimerRunning = false;
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("textTitle")
                        .setContentText(mHelper.getCommentData())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                Log.d("HumariApp", "onFinish: " );

            }
        }.start();
        //  mTimerRunning = true;
    }



//    private void resetTimer() {
//        mtimeLeftInMillis = mStartTimeInMillis;
//    }

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