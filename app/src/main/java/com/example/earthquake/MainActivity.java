package com.example.earthquake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<EarthQuake> arrayList = new ArrayList<>();
//        arrayList.add(new EarthQuake("1","A","101010"));
//        arrayList.add(new EarthQuake("2","B","101011"));
//        arrayList.add(new EarthQuake("3","C","101012"));
//        arrayList.add(new EarthQuake("4","D","101013"));
//        arrayList.add(new EarthQuake("1","A","101010"));
//        arrayList.add(new EarthQuake("2","B","101011"));
//        arrayList.add(new EarthQuake("3","C","101012"));
//        arrayList.add(new EarthQuake("4","D","101013"));
//        arrayList.add(new EarthQuake("1","A","101010"));
//        arrayList.add(new EarthQuake("2","B","101011"));
//        arrayList.add(new EarthQuake("3","C","101012"));
//        arrayList.add(new EarthQuake("4","D","101013"));

        ArrayList<EarthQuake> earthQuakes = QueryUtils.extractEarthquakes();

        final CustoumEQadapter custoumEQadapter = new CustoumEQadapter(this,earthQuakes);
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
    }
}