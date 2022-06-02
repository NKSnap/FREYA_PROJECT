package com.firstapplication.freya.view.signin.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.haircut.HaircutPresenter;
import com.firstapplication.freya.view.signin.fragment.LogInFragment;

public class SingInActivity extends AppCompatActivity {
    HaircutPresenter haircutPresenter = new HaircutPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        haircutPresenter.readDateAndTime();

        Intent intent = getIntent();
        int key = intent.getIntExtra("key", 0);

        LogInFragment fragment = new LogInFragment();
        if (key == -1) {
            Bundle args = new Bundle();
            args.putInt("key", -1);
            fragment.setArguments(args);
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_login, fragment).commit();
    }
}
