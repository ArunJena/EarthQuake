package com.example.earthquake;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<EarthQuake>>{

 private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";

    ListView listView;
    final CustoumEQadapter custoumEQadapter=null;
    Context ctx=MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        //Request for data from AsyncTaskLoader via initLoader
        getSupportLoaderManager().initLoader(1,null,this);
//        BackGround backGround = new BackGround(this,listView);
//        backGround.execute(USGS_REQUEST_URL);
       // Log.i("MainActivity","Our jsonString is "+jsonString);
    }

    @NonNull
    @Override
    public Loader<ArrayList<EarthQuake>> onCreateLoader(int id, @Nullable Bundle args) {
        ListView listView = (ListView)findViewById(R.id.list);
        return new BGAsyncTaskLoader(MainActivity.this,listView,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<EarthQuake>> loader, ArrayList<EarthQuake> data) {

        final CustoumEQadapter custoumEQadapter = new CustoumEQadapter(ctx,data);
        listView.setAdapter(custoumEQadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EarthQuake current = data.get(position);
                String url = current.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                ctx.startActivity(i);
            }
        });
       // Log.i("onPostExecute","Our jsonString is "+jsonString);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<EarthQuake>> loader) {

    }
}