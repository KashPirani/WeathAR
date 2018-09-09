package com.google.ar.core.examples.unity.helloar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivityFragment extends Fragment {

    private Button mArButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_main, container, false);
        mArButton = (Button) view.findViewById(R.id.camera_button);
        mArButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = getActivity().getIntent();
                Bundle activityBundle = activityIntent.getExtras();
                CurrentWeatherObj deserializedWeatherObj=
                        (CurrentWeatherObj) activityBundle.getSerializable("weatherObj");
                Intent myIntent = new Intent(getActivity(), UnityPlayerActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
        return view;
    }
}