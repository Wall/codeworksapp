package com.bluewall.trafficalarm;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import Tasks.GetConfigTask;
import Tasks.GetEventsTask;
import Tasks.GetRouteDataTask;

//import com.bluewall.trafficalarm.Tasks.GetConfigTask;
//import com.bluewall.trafficalarm.Tasks.GetEventTask;
import com.bluewall.trafficalarm.model.Route;
import com.bluewall.trafficalarm.adapter.AlarmAdapter;
import com.bluewall.trafficalarm.model.Alarm;
import com.bluewall.trafficalarm.services.AlarmCheckService;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = "Menu";

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));



       // Log.i("Hello", "");
      //  new GetEventTask(this).run();
        new GetConfigTask(this).run();
        new GetRouteDataTask(this,new Route("")).run();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
                mTitle = "TRAFFIC ALARM";
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_add_alarm) {
            Intent intent = new Intent(MainActivity.this, CreateAlarmActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Button btnStartAlarm = (Button) rootView.findViewById(R.id.btn_start_alarm);
            btnStartAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startAlarmService();
                }
            });
            Button btnStopAlarm = (Button) rootView.findViewById(R.id.btn_stop_alarm);
            btnStopAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stopCurrentAlarm();
                }
            });
            ListView listView = (ListView) rootView.findViewById(R.id.list_alarms);
            Alarm alarm = new Alarm();
            alarm.setAlarmID(1);
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.HOUR, +3);


            alarm.setArrivalTime(cal.getTime());
            boolean[] daysBoolean = {false,true,true,true,false,false};
            alarm.setDaysOfTheWeek(daysBoolean);
            alarm.setPrepTime(1);
            alarm.setDefaultTime(cal.getTime());
            List<Alarm> alarmList = new LinkedList<Alarm>();
            alarmList.add(alarm);
            AlarmAdapter adapter = new AlarmAdapter(getActivity(), alarmList);
            listView.setAdapter(adapter);
            return rootView;
        }

        private void startAlarmService(){

            Intent intent = new Intent(getActivity(), AlarmCheckService.class);
            intent.putExtra("alarm", "Toast this message");
            intent.putExtra("alarmTone", Settings.System.RINGTONE);
            PendingIntent pendingIntent = PendingIntent.getService(getActivity(), 0,intent,0);

            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 5000, pendingIntent);
            //getActivity().startService(intent);

        }

        private void stopCurrentAlarm(){
            Intent intent = new Intent(getActivity(), AlarmCheckService.class);
            intent.putExtra("alarm", "Toast this message");
            PendingIntent pendingIntent = PendingIntent.getService(getActivity(), 0,intent,0);

            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
