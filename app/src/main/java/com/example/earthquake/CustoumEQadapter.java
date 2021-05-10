package com.example.earthquake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.graphics.drawable.GradientDrawable;


public class CustoumEQadapter extends ArrayAdapter {

    private static final String LOCATION_SEPARATOR = " of ";
    public CustoumEQadapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
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

        //Set magnitude || use Decimal Format
        {
            double mag = earthQuake.getMag();
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            String magnitude = decimalFormat.format(mag);

            TextView mag_tv = (TextView)listItemView.findViewById(R.id.mag);
            mag_tv.setText(magnitude);

            //Set circle Colour
            GradientDrawable magnitudeCircle = (GradientDrawable)mag_tv.getBackground();
            int magnitudeColor = getMagnitudeColor(mag);
            magnitudeCircle.setColor(magnitudeColor);
        }
        //City---> direction + cityname
        {
            String direction;
            String cityName;
            String city = earthQuake.getCity();
            if(city.contains(LOCATION_SEPARATOR)){
                String parts[] = city.split(LOCATION_SEPARATOR);
                direction = parts[0]+LOCATION_SEPARATOR;
                cityName = parts[1];
            }else {
                direction = getContext().getString(R.string.near_the);
                cityName = city;
            }
            TextView direction_tv = (TextView)listItemView.findViewById(R.id.direction);
            TextView cityName_tv = (TextView)listItemView.findViewById(R.id.city);
            direction_tv.setText(direction);
            cityName_tv.setText(cityName);
        }
        //Format date and time into readable form
        {
            long timeInMilliseconds = Long.parseLong(earthQuake.getDate());
            Date dateObject = new Date(timeInMilliseconds);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM DD, yyyy");
            String date = simpleDateFormat.format(dateObject);
            TextView date_tv = (TextView) listItemView.findViewById(R.id.date);
            date_tv.setText(date);

            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
            String time = timeFormat.format(dateObject);
            TextView time_tv = (TextView) listItemView.findViewById(R.id.time);
            time_tv.setText(time);

        }




        return listItemView;
    }
}
