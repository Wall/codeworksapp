package com.bluewall.trafficalarm.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bluewall.trafficalarm.R;

import java.util.Calendar;

public class StepFourFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static StepFourFragment newInstance(int sectionNumber) {
        StepFourFragment fragment = new StepFourFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public StepFourFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_four, container, false);
        ImageButton btnSelectDate = (ImageButton)rootView.findViewById(R.id.button_select_date);
        ImageButton btnSelectTime = (ImageButton)rootView.findViewById(R.id.button_select_time);
        final TextView txtTime = (TextView)rootView.findViewById(R.id.edit_text_time);
        final TextView txtDate = (TextView)rootView.findViewById(R.id.edit_text_date);
        final LinearLayout llDaysOfTheWeek = (LinearLayout)rootView.findViewById(R.id.ll_days_of_week);
        final RelativeLayout rlDatePicker = (RelativeLayout)rootView.findViewById(R.id.rl_date_picker);
        RadioButton rbOnce = (RadioButton)rootView.findViewById(R.id.rb_once);
        RadioButton rbRepeat = (RadioButton)rootView.findViewById(R.id.rb_repeat);
        rbOnce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    llDaysOfTheWeek.setVisibility(View.GONE);
                    rlDatePicker.setVisibility(View.VISIBLE);
                }
            }
        });

        rbRepeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    llDaysOfTheWeek.setVisibility(View.VISIBLE);
                    rlDatePicker.setVisibility(View.GONE);
                }
            }
        });

        rbOnce.setChecked(true);
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment(txtDate);
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment newFragment = new TimePickerFragment(txtTime);
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        return rootView;
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        TextView textTime;
        public TimePickerFragment(TextView text){
            textTime = text;
        }
        @Override

        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, false);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
           textTime.setText("" + hourOfDay + " : " + minute);
        }

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
    TextView txtDate;

        public DatePickerFragment(TextView text){
            txtDate = text;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
           txtDate.setText((new StringBuilder().append(day).append(month + 1)
                   .append("-").append("-").append(year)
                   .append(" ")));
        }
    }
}
