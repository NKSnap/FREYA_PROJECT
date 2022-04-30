package com.firstapplication.freya.view.signin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firstapplication.freya.MainActivity;
import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.signin.SingInPresenter;
import com.firstapplication.freya.view.registration.activity.RegistrationActivity;


public class LogInFragment extends Fragment implements View.OnClickListener {
    Button btnRegister, btnLogin, btnForgotPass;
    EditText etEmail, etPassword;
    Context context;
    SingInPresenter presenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        presenter = new SingInPresenter(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etEmail = view.findViewById(R.id.et_log_email);
        etPassword = view.findViewById(R.id.et_log_password);

        btnRegister = view.findViewById(R.id.btn_not_registered);
        btnLogin = view.findViewById(R.id.btn_login);
        btnForgotPass = view.findViewById(R.id.btn_forgot_password);

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnForgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int selectId = v.getId();
        switch (selectId) {
            case (int) R.id.btn_not_registered:
                registerNewAccount();
                break;
            case (int) R.id.btn_forgot_password:
                changePassword();
                break;
            case (int) R.id.btn_login:
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(email, password);
                break;
        }
    }

    private void changePassword() {
        // Change password of this User
    }

    private void loginUser(String email, String password) {
        // Login this User
    }

    private void registerNewAccount() {
        // Open activity for registration
        Intent intent = new Intent(context, RegistrationActivity.class);
        startActivity(intent);
    }

}