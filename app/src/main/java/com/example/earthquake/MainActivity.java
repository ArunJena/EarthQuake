package com.example.earthquake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

 private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";
    private String jsonString="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BackGround backGround = new BackGround();
        backGround.execute(USGS_REQUEST_URL);
        Log.i("MainActivity","Our jsonString is "+jsonString);
    }
    class BackGround extends AsyncTask<String,Void,Void>{
        @Override
        protected void onPostExecute(Void aVoid) {
            ArrayList<EarthQuake> earthQuakes = QueryUtils.extractEarthquakes(jsonString);

            final CustoumEQadapter custoumEQadapter = new CustoumEQadapter(getApplicationContext(),earthQuakes);
            ListView listView = (ListView)findViewById(R.id.list);
            listView.setAdapter(custoumEQadapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    EarthQuake current = earthQuakes.get(position);
                    String url = current.getUrl();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
            Log.i("onPostExecute","Our jsonString is "+jsonString);
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(String... strings) {
            String urlString = strings[0];
            URL url = null;
            HttpURLConnection httpURLConnection=null;
            InputStream inputStream=null;

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
            return null;
        }
    }
}