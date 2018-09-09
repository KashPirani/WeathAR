package com.google.ar.core.examples.unity.helloar;


import android.Manifest;
import android.app.DialogFragment;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    double _latitude;
    double _longitude;
    CurrentWeatherObj weatherObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Main"));
        tabLayout.addTab(tabLayout.newTab().setText("AR View"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //async call
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String date = sdf.format(c.getTime());

        getLocation();

        GetWeather getWeatherTask = new GetWeather(_latitude, _longitude, date);
        getWeatherTask.execute();

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void getLocation()
    {
        LocationManager locationManager = (LocationManager)getSystemService(this.LOCATION_SERVICE);
        if ((ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
        else
        {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(location!=null)
            {
                _latitude = location.getLatitude();
                _longitude = location.getLongitude();

            }
            else
            {
                _latitude = 0;
                _longitude = 0;
                Log.e("weatherAR", "Can't get location");
            }

        }
    }

    public void fragGetWeather(String fragDate)
    {

        GetWeather getWeatherTask = new GetWeather(_latitude, _longitude, fragDate);
        getWeatherTask.execute();
    }

    public void onButtonClicked(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"Date Picker");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                getLocation();
                break;
            }
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private class GetWeather extends AsyncTask<Void, Void, CurrentWeatherObj> {

        private static final String OPEN_WEATHER_MAP_API =
                "http://api.openweathermap.org/data/2.5/forecast?lat=%f&lon=%f&units=metric";

        private static final String openWeatherMapApiKey =
                "9ec51f58d703f446d3eb654e67e0422c";

        private double _lat;
        private double _lon;
        private String _date;

        public GetWeather(double lat, double lon, String date){
            super();
            this._lat = lat;
            this._lon = lon;
            this._date = date;
        }

        public GetWeather() {
            super();
        }

        @Override
        protected CurrentWeatherObj doInBackground(Void... params){
            try {
                URL url = new URL(String.format(OPEN_WEATHER_MAP_API, _lat, _lon));
                HttpURLConnection connection =
                        (HttpURLConnection)url.openConnection();

                connection.addRequestProperty("x-api-key", openWeatherMapApiKey);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                StringBuffer json = new StringBuffer(1024);
                String tmp="";
                while((tmp=reader.readLine())!=null)
                    json.append(tmp).append("\n");
                reader.close();

                JSONObject data = new JSONObject(json.toString());

                //checks to see if the request was a success
                if(data.getInt("cod") != 200){
                    return null;
                }

                return WeatherParser.parseJson(data, _date);

            }catch(Exception e){
                return null;
            }
        }

        @Override
        protected void onPostExecute(CurrentWeatherObj result) {
            if (result == null) {
                //do something
            } else {
                weatherObj = result;
                Bundle bundle = new Bundle();
                bundle.putSerializable("weatherObj", weatherObj);
                getIntent().putExtras(bundle);

            }
        }
    }
}
