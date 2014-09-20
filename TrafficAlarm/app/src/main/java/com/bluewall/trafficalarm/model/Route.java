package com.bluewall.trafficalarm.model;

/**
 * Created by Gary on 20/09/2014.
 */
public class Route {

    private String route;
    private long standardTravelTime;

    public Route(String route, long standardTravelTime) {
        this.route = route;
        this.standardTravelTime = standardTravelTime;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public long getStandardTravelTime() {
        return standardTravelTime;
    }

    public void setStandardTravelTime(long standardTravelTime) {
        this.standardTravelTime = standardTravelTime;
    }
}
