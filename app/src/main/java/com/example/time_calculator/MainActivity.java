package com.example.time_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    ArrayList<Date> timeArray1 = new ArrayList<>();
    ArrayList<Date> timeArray2 = new ArrayList<>();
    ArrayList<String> timepick= new ArrayList<>();
    ArrayList<String> timetravel= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Handler handler = new Handler();

        final int numberOfIterations = 10;
        final long delayBetweenIterationsMillis = 5000; // 5 seconds

        for (int i = 0; i < numberOfIterations; i++) {
            // Save the time before the delay (t1)
            Date t1 = new Date();
             // Apply the delay to t2
            timeArray1.add(t1);

            try {
                Thread.sleep(250*i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Date t2=new Date();
            timeArray2.add(t2);
            try {
                Thread.sleep(500*i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for(int m = 0 ; m < numberOfIterations; m++) {
             //picking time\
            Log.d("timming", "onCreate: m " + m);

            timepick.add(CalculateTime(timeArray1.get(m), timeArray2.get(m)));
//            String result = addTime(time1, time2);

        }
        String result = "Days: 0 Hours: 0 Minutes: 0 Seconds: 0";

        // Add all time intervals in the ArrayList
        for (String time : timepick) {
            Log.d("timming pick", "onCreate: " + time);
            result = addTime(result, time);
        }
        Log.d("timming total pick ...", "onCreate: " + result);

        for(int n=0; n < numberOfIterations-1;n++)
        {
            //travel time
            Log.d("timming", "onCreate: n" + n);
            timetravel.add(CalculateTime(timeArray2.get(n), timeArray1.get(n+1)));
        }
        for (String timepick : timetravel) {
            Log.d("timming timetravel", "onCreate: " + timepick);
            result = addTime(result, timepick);
        }
        Log.d("timming total travel  ...", "onCreate: " + result);
    }
    public static String addTime(String time1, String time2) {
        // Parse the input strings to extract individual components
        String[] time1Components = time1.split(" ");
        String[] time2Components = time2.split(" ");

        int days1 = Integer.parseInt(time1Components[1]);
        int hours1 = Integer.parseInt(time1Components[3]);
        int minutes1 = Integer.parseInt(time1Components[5]);
        int seconds1 = Integer.parseInt(time1Components[7]);

        int days2 = Integer.parseInt(time2Components[1]);
        int hours2 = Integer.parseInt(time2Components[3]);
        int minutes2 = Integer.parseInt(time2Components[5]);
        int seconds2 = Integer.parseInt(time2Components[7]);

        // Convert everything to seconds and perform addition
        int totalSeconds = days1 * 24 * 60 * 60 + hours1 * 60 * 60 + minutes1 * 60 + seconds1
                + days2 * 24 * 60 * 60 + hours2 * 60 * 60 + minutes2 * 60 + seconds2;

        // Convert total seconds back to individual components
        int days = totalSeconds / (24 * 60 * 60);
        totalSeconds = totalSeconds % (24 * 60 * 60);
        int hours = totalSeconds / (60 * 60);
        totalSeconds = totalSeconds % (60 * 60);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        // Create a formatted string representing the result
        return String.format("Days: %d Hours: %d Minutes: %d Seconds: %d",
                days, hours, minutes, seconds);
    }
    public String CalculateTime(Date t1, Date t2)
    {
        long timeDifference = t2.getTime() - t1.getTime();

        // Convert the time difference to appropriate units (e.g., seconds, minutes, hours)
        long seconds = timeDifference / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        // You can also get the remaining hours, minutes, and seconds
        long remainingSeconds = seconds % 60;
        long remainingMinutes = minutes % 60;
        long remainingHours = hours % 24;

       //  Log the time difference
        Log.d("Time Difference", "Days: " + days +
                " Hours: " + remainingHours +
                " Minutes: " + remainingMinutes +
                " Seconds: " + remainingSeconds);

        return   "Days: " + days +
            " Hours: " + remainingHours +
            " Minutes: " + remainingMinutes +
            " Seconds: " + remainingSeconds;
    }
}