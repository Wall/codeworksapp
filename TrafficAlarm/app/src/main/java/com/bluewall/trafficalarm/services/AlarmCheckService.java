package com.bluewall.trafficalarm.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

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
        }else{
            Log.d("Service", "No string extra");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
