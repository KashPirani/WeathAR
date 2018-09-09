package com.google.ar.core.examples.unity.helloar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        dialog.getDatePicker().setMinDate(c.getTimeInMillis());
        c.set(year, month, day+5);
        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        return dialog;
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String fragDate;

        if(month<9 && day <10) {
            fragDate = year + "-0"+(month + 1) + "-0"+ day;
        }
        else if(month<9 && day>9)
        {
            fragDate = year + "-0"+(month + 1) + "-" + day;
        }
        else if(month>8 && day<10)
        {
            fragDate = year + "-" + (month + 1) + "-0"+day;

        }
        else{
            fragDate = year + "-" + (month + 1) + "-" + day;

        }

        ((MainActivity)getActivity()).fragGetWeather(fragDate);
    }
}

