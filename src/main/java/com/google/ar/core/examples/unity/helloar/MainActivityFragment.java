package com.google.ar.core.examples.unity.helloar;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityFragment extends Fragment {

    private FloatingActionButton mArButton;
    private TextView dateTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.landing_fragment, container, false);
        mArButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        mArButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = getActivity().getIntent();
                Bundle activityBundle = activityIntent.getExtras();
                CurrentWeatherObj deserializedWeatherObj=
                        (CurrentWeatherObj) activityBundle.getSerializable("weatherObj");
                Intent myIntent = new Intent(getActivity(), UnityPlayerActivity.class);
                myIntent.putExtras(activityBundle);
                getActivity().startActivity(myIntent);
            }
        });

        dateTextView = (TextView) view.findViewById(R.id.dateView);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String date = sdf.format(c.getTime());
        dateTextView.setText("Pick a date");


//        dateTextView = (TextView) view.findViewById(R.id.dateView);
//        Intent activityIntent2 = getActivity().getIntent();
//        Bundle activityBundle2 = activityIntent2.getExtras();
//        String date;
//        if (activityBundle2.containsKey("date")) {
//            date = (String) activityBundle2.get("date");
//        } else {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Calendar c = Calendar.getInstance();
//            date = sdf.format(c.getTime());
//        }
//        dateTextView.setText("Current Weather Day: " + date);


//        dateTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogFragment newFragment = new DatePickerFragment();
//                newFragment.show(getFragmentManager(), "Date Picker");
//                newFragment.show(getFragmentManager(),"Date Picker");
//            }
//        });

        return view;
    }
}