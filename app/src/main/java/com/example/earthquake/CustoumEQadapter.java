package com.example.earthquake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustoumEQadapter extends ArrayAdapter {
    public CustoumEQadapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.each_item,parent,false);
        }
        EarthQuake earthQuake = (EarthQuake)getItem(position);

        TextView mag = (TextView)listItemView.findViewById(R.id.mag);
        mag.setText(earthQuake.getMag());
        TextView city = (TextView)listItemView.findViewById(R.id.city);
        city.setText(earthQuake.getCity());
//        TextView date = (TextView)listItemView.findViewById(R.id.date);
//        date.setText(earthQuake.getDate());

        //Format date and time into readable form
        long timeInMilliseconds = Long.parseLong(earthQuake.getDate());
        Date dateObject = new Date(timeInMilliseconds);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM DD, yyyy");
        String date = simpleDateFormat.format(dateObject);
        TextView date_tv = (TextView)listItemView.findViewById(R.id.date);
        date_tv.setText(date);

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String time = timeFormat.format(dateObject);
        TextView time_tv = (TextView)listItemView.findViewById(R.id.time);
        time_tv.setText(time);



        return listItemView;
    }
}
