package com.firstapplication.freya.view.registration.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.view.registration.fragment.PersonalDataFragment;
import com.firstapplication.freya.view.registration.fragment.RegistrationDataFragment;

public class RegistrationActivity extends AppCompatActivity implements PersonalDataFragment.DataSender {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    @Override
    public void onDataSend(RegistrationData registrationData) {
        RegistrationDataFragment registrationDataFragment =
                (RegistrationDataFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_container_reg_data);

        if (registrationDataFragment != null)
            registrationDataFragment.getData(registrationData);
    }
}