package com.firstapplication.freya.view.account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.view.account.fragment.AccountDataFragment;
import com.firstapplication.freya.view.signin.activity.SingInActivity;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {
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

        findViewById(R.id.btn_get_out).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_get_out) {
            Intent intent = new Intent(this, SingInActivity.class);
            intent.putExtra("key", -1);
            startActivity(intent);
            finish();
        }
    }
}
















