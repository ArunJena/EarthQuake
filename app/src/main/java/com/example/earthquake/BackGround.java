package com.example.earthquake;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

    class BackGround extends AsyncTask<String,Void,Void> {

        private String jsonString="";
        Context ctx ;
        ListView listView;

        public BackGround(Context ctx,ListView listView) {
            this.ctx = ctx;
            this.listView=listView;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ArrayList<EarthQuake> earthQuakes = QueryUtils.extractEarthquakes(jsonString);

            final CustoumEQadapter custoumEQadapter = new CustoumEQadapter(ctx,earthQuakes);
            listView.setAdapter(custoumEQadapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    EarthQuake current = earthQuakes.get(position);
                    String url = current.getUrl();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    ctx.startActivity(i);
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

