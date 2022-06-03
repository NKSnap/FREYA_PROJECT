package com.firstapplication.freya.view.haircut.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.view.haircut.fragment.HaircutInformationFragment;
import com.firstapplication.freya.view.haircut.fragment.InformationChangeFragment;


public class HaircutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haircut);

        RegistrationData rd = (RegistrationData) getIntent().getSerializableExtra("user");

        InformationChangeFragment fragment1 = new InformationChangeFragment();
        HaircutInformationFragment fragment2 = new HaircutInformationFragment();

        Bundle args = new Bundle();
        args.putSerializable("user", rd);
        fragment1.setArguments(args);
        fragment2.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_change_info, fragment1).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_haircut_info, fragment2).commit();
    }
}