package com.bluewall.trafficalarm.model;

/**
 * Created by Gary on 20/09/2014.
 */
public class RealTimeConfig {

    public String configUrl;
    public String routeUrl;
    public String progressUrl;
    public String eventsUrl;

    public RealTimeConfig(String configUrl, String routeUrl, String progressUrl, String eventsUrl) {
        this.configUrl = configUrl;
        this.routeUrl = routeUrl;
        this.progressUrl = progressUrl;
        this.eventsUrl = eventsUrl;
    }
}
