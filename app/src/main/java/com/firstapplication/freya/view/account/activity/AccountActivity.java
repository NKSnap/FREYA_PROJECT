package com.firstapplication.freya.view.account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.TextView;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.view.account.fragment.AccountDataFragment;

public class AccountActivity extends AppCompatActivity {
    private RegistrationData rd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        rd = (RegistrationData) getIntent().getSerializableExtra("user");
        AccountDataFragment fragment = new AccountDataFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", rd);
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_account_data, fragment).commit();
    }
}