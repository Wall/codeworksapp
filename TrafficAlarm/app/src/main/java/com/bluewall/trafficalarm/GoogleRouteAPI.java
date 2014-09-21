package com.bluewall.trafficalarm;

import java.io.IOException;
import java.io.InputStream;

import java.net.ProtocolException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;


public class GoogleRouteAPI {

    private static final String ENCODE = "UTF-8";
    //private static String BASE_URL = "http://maps.googleapis.com/maps/api/distancematrix/";
    private static String BASE_URL = "https://maps.googleapis.com/maps/api/directions/";

    public static String shortestDistance(String origin, String destination) {

        String params = null;
        try {
            params = "json?origin=" + URLEncoder.encode(origin, "UTF-8") + "&destination=" + URLEncoder.encode(destination, "UTF-8") + "&key=" + Constants.GOOGLE_API_KEY;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("PARAMS: " + params);
        return query(params);
    }

    public static String shortestDistance(double startx, double starty, double destx, double desty) {
        String params ="json?origin=" + startx + "," + starty + "&destination=" + destx + "," + desty;
        return query(params);
    }

    private static String query(String params) {
        String rawData = params;
        String type = "application/x-www-form-urlencoded";
        String encodedData = null;
        try {
            encodedData = URLEncoder.encode(rawData, ENCODE);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        URL url;
        OutputStream os = null;
        InputStream is = null;
        try {
            url = new URL(BASE_URL + params);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            try {
                conn.setRequestMethod("POST");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            conn.setRequestProperty("Content-Type", type);
            //conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));

            os = conn.getOutputStream();

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, ENCODE));
            writer.write(params);
            writer.flush();
            writer.close();
            os.close();

            conn.connect();
            is = conn.getInputStream();
            return readStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String readStream(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String result, line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = line;
        try {
            while((line = reader.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return readJson(result);
    }

    private static String readJson(String json) {
        //Gson gson = new Gson();
        return json;
    }
}
