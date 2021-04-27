package com.example.earthquake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<EarthQuake> arrayList = new ArrayList<>();
        arrayList.add(new EarthQuake("1","A","101010"));
        arrayList.add(new EarthQuake("2","B","101011"));
        arrayList.add(new EarthQuake("3","C","101012"));
        arrayList.add(new EarthQuake("4","D","101013"));
        arrayList.add(new EarthQuake("1","A","101010"));
        arrayList.add(new EarthQuake("2","B","101011"));
        arrayList.add(new EarthQuake("3","C","101012"));
        arrayList.add(new EarthQuake("4","D","101013"));
        arrayList.add(new EarthQuake("1","A","101010"));
        arrayList.add(new EarthQuake("2","B","101011"));
        arrayList.add(new EarthQuake("3","C","101012"));
        arrayList.add(new EarthQuake("4","D","101013"));

        CustoumEQadapter custoumEQadapter = new CustoumEQadapter(this,arrayList);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(custoumEQadapter);
    }
}