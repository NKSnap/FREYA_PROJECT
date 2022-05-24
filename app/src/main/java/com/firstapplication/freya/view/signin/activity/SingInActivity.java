package com.firstapplication.freya.view.signin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.signin.SingInPresenter;
import com.firstapplication.freya.view.account.fragment.AccountDataFragment;
import com.firstapplication.freya.view.signin.fragment.LogInFragment;

public class SingInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

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
