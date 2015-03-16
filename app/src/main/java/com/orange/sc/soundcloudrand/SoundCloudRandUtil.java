package com.orange.sc.soundcloudrand;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by pierre on 13/03/2015.
 */
public class SoundCloudRandUtil {

    public static JSONObject parseAnswer(HttpResponse httpResponse)
            throws Exception {

        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = statusLine.getStatusCode();

        JSONObject response = null;
        StringBuilder stringBuilder = new StringBuilder();
        HttpEntity httpentity = httpResponse.getEntity();
        InputStream inputStream = httpentity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        inputStream.close();

        if (statusCode == 200) {
            try {
                response = new JSONObject(stringBuilder.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return response;

    }



    public static JSONArray parseAnswerArray(HttpResponse httpResponse)
            throws Exception {

        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = statusLine.getStatusCode();

        JSONArray response = null;
        StringBuilder stringBuilder = new StringBuilder();
        HttpEntity httpentity = httpResponse.getEntity();
        InputStream inputStream = httpentity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        inputStream.close();

        if (statusCode == 200) {
            try {
                response = new JSONArray(stringBuilder.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return response;

    }

    public static ArrayList<String> getTrackIdListFromResponse(JSONArray response) throws JSONException {

        ArrayList<String> trackIdList = new ArrayList<String>();
        for(int i = 0; i < response.length(); i++){

            if(response.getJSONObject(i).getString("kind").equalsIgnoreCase("track"))
                trackIdList.add(i,response.getJSONObject(i).getString("id"));
        }
        return trackIdList;
    }
}
