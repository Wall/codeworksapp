package com.bluewall.trafficalarm;

import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import android.content.SharedPreferences;

public class FragmentSettings extends PreferenceFragment {

    private static String DEFAULT_PREP_TIME = "60";
    private static String DEFAULT_ALARM_INTERVAL = "5";

    private static SharedPreferences sharedPrefs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        sharedPrefs = getPreferenceManager().getDefaultSharedPreferences(getActivity());
    }

    public String getPreferencePrepTime() {
        String value = ((EditTextPreference) findPreference(getString(R.string.key_prep_time))).getText();
        if (value == null || value.equals(""))
            return DEFAULT_PREP_TIME;
        return value;
    }

    public String getPreferenceAlarmInterval() {
        String value = ((EditTextPreference) findPreference(getString(R.string.key_alarm_interval))).getText();
        if (value == null || value.equals(""))
            return DEFAULT_ALARM_INTERVAL;
        return value;
    }


}
