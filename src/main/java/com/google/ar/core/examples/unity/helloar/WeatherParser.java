package com.google.ar.core.examples.unity.helloar;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherParser {

    public static CurrentWeatherObj parseJson(JSONObject json, String _date) {
        CurrentWeatherObj dayWeather = new CurrentWeatherObj();

        int counter = 0;
        try {
//            String dtText = dayData.getString("dt_text");
//            //finds the item in the list that matches the correct date
//            while(!(dtText.toLowerCase().contains(_date.toLowerCase())))
//            {
//                counter++;
//                dayData = json.getJSONArray("list").getJSONObject(counter);
//                String dtText = dayData.getString("dt_text");
//            }

            //dayDate should be initialized before any of the lines below the while run . . .
            JSONObject dayData;
            String dtText;
            do {
                dayData = json.getJSONArray("list").getJSONObject(counter);
                dtText = dayData.getString("dt_txt");
                counter++;
            } while (!(dtText.toLowerCase().contains(_date.toLowerCase())));

            JSONObject mainData = dayData.getJSONObject("main");
            JSONObject weather = dayData.getJSONArray("weather").getJSONObject(0);
            JSONObject wind = dayData.getJSONObject("wind");
            try {
                JSONObject clouds = dayData.getJSONObject("clouds");
                dayWeather.setClouds(clouds.getInt("all"));
            }
            catch(JSONException e)
            {
                Log.e("WeatherAR", "unexpected JSON exception", e);
                dayWeather.setClouds(0);
            }
            try {
                JSONObject snow = dayData.getJSONObject("snow");
                dayWeather.setSnow(snow.getDouble("3h"));
            }
            catch(JSONException e)
            {
                Log.e("WeatherAR", "unexpected JSON exception", e);
                dayWeather.setSnow(0);
            }
            try {
                JSONObject rain = dayData.getJSONObject("rain");
                dayWeather.setRain(rain.getDouble("3h"));
            }
            catch(JSONException e)
            {
                Log.e("WeatherAR", "unexpected JSON exception", e);
                dayWeather.setRain(0);
            }
            dayWeather.setTempMax(mainData.getDouble("temp_max"));
            dayWeather.setTempMin(mainData.getDouble("temp_min"));
            dayWeather.setPressure(mainData.getDouble("pressure"));
            dayWeather.setHumidity(mainData.getInt("humidity"));

            dayWeather.setGenDescription(weather.getString("description"));
            dayWeather.setWeatherId(weather.getInt("id"));

            dayWeather.setWindSpeed(wind.getDouble("speed"));

            dayWeather.setDateTime(dayData.getString("dt_txt"));

            int weatherIndex = weather.getInt("id");

            switch(weatherIndex/100) {
                case 2:
                    dayWeather.setPrecipitation("thunderstorm");
                    break;

                case 3:
                    dayWeather.setPrecipitation("drizzle");
                    break;

                case 5:
                    dayWeather.setPrecipitation("rain");
                    break;

                case 6:
                    dayWeather.setPrecipitation("snow");
                    break;

                case 7:
                    dayWeather.setPrecipitation("obscured");
                    break;

                default:
                    dayWeather.setPrecipitation("none");
            }

        } catch(JSONException e)
        {
            Log.e("WeatherAR", "unexpected JSON exception", e);
        }
        return dayWeather;
    }

}
