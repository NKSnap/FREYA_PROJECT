package com.firstapplication.freya.view.registration.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firstapplication.freya.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class PersonalDataFragment extends Fragment {

    Button date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = view.findViewById(R.id.btn_reg_date);
        setDefaultDate(date);
    }

    private void setDefaultDate(Button view){
        Calendar calendar = new GregorianCalendar();

        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1)
            day = "0" + day;

        String month = String.valueOf((calendar.get(Calendar.MONTH) + 1));
        if (month.length() == 1)
            month = "0" + month;

        String nowDate = day + "." + month + "." + calendar.get(Calendar.YEAR);
        view.setText(nowDate);
    }

}