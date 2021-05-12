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


        }

        @Override
        protected Void doInBackground(String... strings) {

            return null;
        }
    }

