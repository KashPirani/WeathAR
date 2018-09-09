package com.google.ar.core.examples.unity.helloar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ThirdTabFragment extends Fragment {

    private ImageView dateImage;
    private ImageView humidityImage;
    private ImageView tempImage;
    private ImageView precipImage;
    private TextView  dateText;
    private TextView  humidityText;
    private TextView  tempText;
    private TextView  precipText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.thirdpage_fragment, container, false);

        dateImage = (ImageView) v.findViewById(R.id.dateImageView);
        humidityImage = (ImageView) v.findViewById(R.id.humidityImageView);
        tempImage = (ImageView) v.findViewById(R.id.tempImageView);
        precipImage = (ImageView) v.findViewById(R.id.precipImageView);

        dateText = (TextView) v.findViewById(R.id.dateTextView);
        humidityText = (TextView) v.findViewById(R.id.humidityTextView);
        tempText = (TextView) v.findViewById(R.id.tempTextView);
        precipText = (TextView) v.findViewById(R.id.precipTextView);

        Intent activityIntent = getActivity().getIntent();
        Bundle activityBundle = activityIntent.getExtras();
        CurrentWeatherObj deserializedWeatherObj=
                (CurrentWeatherObj) activityBundle.getSerializable("weatherObj");

        dateText.setText("Date: " + deserializedWeatherObj.getDateTime());
        humidityText.setText("Humidity: " + deserializedWeatherObj.getHumidity());
        tempText.setText("Temperature: " + deserializedWeatherObj.getTempMax());
        precipText.setText("Precipitation: " + deserializedWeatherObj.getRain()+ " mm.");

        return v;
    }
}