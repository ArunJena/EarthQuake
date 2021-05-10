package com.example.earthquake;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.CDATASection;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Sample JSON response for a USGS query */
    private QueryUtils() {
    }
    public static ArrayList<EarthQuake> extractEarthquakes(String string) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<EarthQuake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject baseJsonResponse = new JSONObject(string);
            JSONArray earthQuakeArray = baseJsonResponse.getJSONArray("features");
            for(int i=0;i<earthQuakeArray.length();i++)
            {
                JSONObject currentJSONObject = earthQuakeArray.getJSONObject(i);
                JSONObject propertiesJSONObject = currentJSONObject.getJSONObject("properties");
                double mag = propertiesJSONObject.getDouble("mag");
                String place = propertiesJSONObject.getString("place");
                String time = propertiesJSONObject.getString("time");
                String url = propertiesJSONObject.getString("url");

            //    Long time = propertiesJSONObject.getLong("time");

              // EarthQuake earthQuake = new EarthQuake(mag,place, time);
                earthquakes.add(new EarthQuake(mag,place,time,url));
            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}