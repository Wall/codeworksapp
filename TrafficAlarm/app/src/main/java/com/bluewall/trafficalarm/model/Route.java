package com.bluewall.trafficalarm.model;

/**
 * Created by Gary on 20/09/2014.
 */
public class Route {

    private String route;
    private long minTravelTime;
    private long maxTravelTime;

    public Route(String route ) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public long getMinTravelTime() {
        return minTravelTime;
    }

    public void setMinTravelTime(long minTravelTime) {
        this.minTravelTime = minTravelTime;
    }

    public long getMaxTravelTime() {
        return maxTravelTime;
    }

    public void setMaxTravelTime(long maxTravelTime) {
        this.maxTravelTime = maxTravelTime;
    }
}
