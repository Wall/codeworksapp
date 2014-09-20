package com.bluewall.trafficalarm.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Gary on 20/09/2014.
 */
public class Alarm {

    private int alarmID;
    private List<Route> routes;
    private Date arrivalTime;
    private Date defaultTime;
    private long prepTime;
    private boolean isOneTime;
    private boolean[] daysOfTheWeek;

    public Alarm(List<Route> routes, Date arrivalTime, long prepTime, boolean isOneTime, boolean[] daysOfTheWeek){
        this.routes = routes;
        this.arrivalTime = arrivalTime;
        this.prepTime = prepTime;
        this.isOneTime = isOneTime;
        this.daysOfTheWeek = daysOfTheWeek;
    }

    public int getAlarmID() {
        return alarmID;
    }

    public void setAlarmID(int alarmID) {
        this.alarmID = alarmID;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(long prepTime) {
        this.prepTime = prepTime;
    }

    public boolean isOneTime() {
        return isOneTime;
    }

    public void setOneTime(boolean isOneTime) {
        this.isOneTime = isOneTime;
    }

    public boolean[] getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public void setDaysOfTheWeek(boolean[] daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }

    public Date getDefaultTime() {
        return defaultTime;
    }

    public void setDefaultTime(Date defaultTime) {
        this.defaultTime = defaultTime;
    }
}
