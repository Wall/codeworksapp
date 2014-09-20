package com.bluewall.trafficalarm.model;

import java.util.List;

/**
 * Created by Gary on 20/09/2014.
 */
public class AlarmList {

    private List<Alarm> alarmList;

    public AlarmList(List<Alarm> alarmList) {
        this.alarmList = alarmList;
    }

    public List<Alarm> getAlarmList() {
        return alarmList;
    }

    public void setAlarmList(List<Alarm> alarmList) {
        this.alarmList = alarmList;
    }

    public Alarm getAlarm(int alarmID){
        for(Alarm alarm: alarmList){
            if(alarm.getAlarmID() == alarmID){
                return alarm;
            }
        }
        return null;
    }

    public void addAlarm(Alarm alarm){
        alarm.setAlarmID(alarmList.size()+1);
        alarmList.add(alarm);
    }
}
