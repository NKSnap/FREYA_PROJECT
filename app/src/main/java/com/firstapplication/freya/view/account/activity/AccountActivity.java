package com.firstapplication.freya.view.account.activity;

import android.os.Bundle;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.view.MenuActivity;
import com.firstapplication.freya.view.account.fragment.AccountDataFragment;
import com.firstapplication.freya.view.account.fragment.RecordsListFragment;


public class AccountActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        RegistrationData rd = (RegistrationData) getIntent().getSerializableExtra("user");
        AccountDataFragment fragment1 = new AccountDataFragment();
        RecordsListFragment fragment2 = new RecordsListFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", rd);
        fragment1.setArguments(args);
        fragment2.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_account_data, fragment1).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_records, fragment2).commit();
    }
}
















