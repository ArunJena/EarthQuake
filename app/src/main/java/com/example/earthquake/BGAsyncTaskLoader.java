package com.example.earthquake;

import android.content.Context;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BGAsyncTaskLoader extends AsyncTaskLoader<ArrayList<EarthQuake>> {
    ListView listView;
    String url;
    public BGAsyncTaskLoader(@NonNull Context context,ListView listView,String url) {
        super(context);
        this.listView=listView;
        this.url=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<EarthQuake> loadInBackground() {
        String urlString = url;
        URL url = null;
        HttpURLConnection httpURLConnection=null;
        InputStream inputStream=null;
        String jsonString="";

        try {
            url=new URL(urlString);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setReadTimeout(10000 /* milliseconds */);
            httpURLConnection.setConnectTimeout(15000 /* milliseconds */);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
//
//                if(httpURLConnection.getResponseCode()==200)
//                    Log.i("MainActivity","We got response code as "+httpURLConnection.getResponseCode());
            inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while(line!=null){
                stringBuilder.append(line);
                line=bufferedReader.readLine();
            }
            jsonString = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<EarthQuake> earthQuakes = QueryUtils.extractEarthquakes(jsonString);
        return earthQuakes;
    }
}
