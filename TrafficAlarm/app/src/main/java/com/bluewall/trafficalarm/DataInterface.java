package com.bluewall.trafficalarm;

import android.util.Log;

import com.bluewall.trafficalarm.model.RealTimeConfig;
import com.bluewall.trafficalarm.model.Route;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Barney on 20/09/2014.
 */
public class DataInterface {

    final static String CONFIG_URL = "http://ec2-54-79-116-215.ap-southeast-2.compute.amazonaws.com/config";

    final static String EVENT_URL = "http://ec2-54-79-116-215.ap-southeast-2.compute.amazonaws.com/events";

    final static String ROUTE_URL = "http://ec2-54-79-116-215.ap-southeast-2.compute.amazonaws.com/route";

    private static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is, "UTF-8");
        s.useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        s.close();

        return response;
    }

    public static RealTimeConfig getConfig() throws IOException, JSONException {

        URL cUrl = new URL(CONFIG_URL);

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(CONFIG_URL);
        httpGet.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials("ttdsUser", "password"),
                "UTF-8", false));

        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity responseEntity = httpResponse.getEntity();

        String json = convertStreamToString(responseEntity.getContent());

        JSONObject jObject  = new JSONObject(json);
        JSONObject resources = jObject.getJSONObject("resources");

        JSONObject self = resources.getJSONObject("self");
        JSONObject events = resources.getJSONObject("events");
        JSONObject progress = resources.getJSONObject("progress");
        JSONObject route = resources.getJSONObject("route");

        String routeConfigUrl = route.getString("href");
        String selfConfigUrl = self.getString("href");
        String progressConfigUrl = progress.getString("href");
        String eventsConfigUrl = events.getString("href");

        RealTimeConfig RTC = new RealTimeConfig(selfConfigUrl,routeConfigUrl,progressConfigUrl,eventsConfigUrl);

        //SharedPrefsUtils.saveConfigFile(,RTC);
        Log.i("STREAM",routeConfigUrl);



        return RTC;
    }

    public static int getEvents() throws IOException {

       // URL cUrl = new URL(CONFIG_URL);
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(EVENT_URL);
        httpGet.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials("ttdsUser", "password"),
                "UTF-8", false));


        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity responseEntity = httpResponse.getEntity();

        Log.i("STREAM",convertStreamToString(responseEntity.getContent()));

        return 0;

    }

    public static Route getRoute(Route routeData) throws IOException, JSONException {

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ROUTE_URL);

        StringEntity params = new StringEntity(routeData.getRoute());
        httpPost.setEntity(params);

        httpPost.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials("ttdsUser", "password"),
                "UTF-8", false));


        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity responseEntity = httpResponse.getEntity();

        Log.i("STREAM",convertStreamToString(responseEntity.getContent()));

        String json = convertStreamToString(responseEntity.getContent());

        JSONObject jObject  = new JSONObject(json);

        String RID = jObject.getString("route-id");
        JSONObject TT = jObject.getJSONObject("travel-time");
        Long routeMinSec = TT.getLong("min-seconds");
        Long routeMaxSec = TT.getLong("max-seconds");

        Route newRouteData = new Route(routeData.getRoute());
        newRouteData.setMinTravelTime(routeMinSec);
        newRouteData.setMaxTravelTime(routeMaxSec);
        newRouteData.setRouteID(RID);

        return newRouteData;

    }
}
