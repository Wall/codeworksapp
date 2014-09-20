package com.bluewall.trafficalarm;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Barney on 20/09/2014.
 */
public class DataInterface {

    final static String CONFIG_URL = "http://ec2-54-79-116-215.ap-southeast-2.compute.amazonaws.com/config";

    private static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is, "UTF-8");
        s.useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        s.close();

        return response;
    }

    public static int getConfig() throws IOException {

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

        Log.i("STREAM",convertStreamToString(responseEntity.getContent()));

        return 0;
    }
}
