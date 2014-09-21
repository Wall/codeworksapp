package com.bluewall.trafficalarm.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bluewall.trafficalarm.R;
import com.bluewall.trafficalarm.model.Alarm;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nefarius on 21/09/2014.
 */
public class AlarmAdapter  extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Alarm> items;


    public AlarmAdapter(Activity activity, List<Alarm> items) {
        this.activity = activity;
        this.items = items;    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item_alarm, null);


        TextView time = (TextView) convertView.findViewById(R.id.text_time);
        TextView days = (TextView) convertView.findViewById(R.id.text_days);


        // getting movie data for the row
        Alarm m = items.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        String time1 = sdf.format(m.getArrivalTime());
        time.setText(time1);

        String daysOfWeek="";
        char[] daysArray= {'S','M','T','W','T','F','S'};
        int count = 0;
        for (boolean b : m.getDaysOfTheWeek()){
            if (b){
                daysOfWeek += "<font color='#f55b5c'>" +daysArray[count] +" </font>";
            }else{
                daysOfWeek += "<font color='#C0C0C0'>" +daysArray[count] +" </font>";
            }
            count++;
        }
        days.setText(Html.fromHtml(daysOfWeek));
        return convertView;
    }

}
