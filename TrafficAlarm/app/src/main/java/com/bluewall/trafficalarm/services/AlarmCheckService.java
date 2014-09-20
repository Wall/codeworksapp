package com.bluewall.trafficalarm.services;

import android.app.IntentService;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.bluewall.trafficalarm.AlarmActivity;

/**
 * Queries the realtime service for the current expected travel time for an alarm.
 * Should be triggered 1hr before the default time of the alarm by an alarm manager
 * Created by Gary on 20/09/2014.
 */
public class AlarmCheckService extends IntentService{


    public AlarmCheckService() {
        super("AlarmCheckService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Service", "Handling intent");
        if(intent.hasExtra("alarm")) {
            String message = intent.getStringExtra("alarm");
            Log.d("Service", message +": " +System.currentTimeMillis());
            Intent alarmIntent = new Intent(getApplicationContext(), AlarmActivity.class);
            alarmIntent.putExtra("alarmID", 1);
            alarmIntent.putExtra("alarmTone", Settings.System.DEFAULT_RINGTONE_URI.toString());
            Log.d("ringtone uri", Settings.System.DEFAULT_RINGTONE_URI.toString());
            alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(alarmIntent);
        }else{
            Log.d("Service", "No string extra");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
