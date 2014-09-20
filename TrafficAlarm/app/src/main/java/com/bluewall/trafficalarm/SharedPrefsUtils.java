package com.bluewall.trafficalarm;

import android.content.Context;
import android.content.SharedPreferences;

import com.bluewall.trafficalarm.model.RealTimeConfig;
import com.google.gson.Gson;

/**
 * Created by Barney on 20/09/2014.
 */
public class SharedPrefsUtils {
    public static void setString(String key, String value, Context context,
                                 String file) {
        SharedPreferences prefs = context.getSharedPreferences(file, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(
                "AlarmApp", 0);
        return prefs.getString(key, null);
    }

    public static void saveConfigFile(Context context,RealTimeConfig RTC){
        Gson gs = new Gson();


       SharedPrefsUtils.setString("ConfFile",
               gs.toJson(RTC),
                context.getApplicationContext(), "AlarmApp");

    }
}
