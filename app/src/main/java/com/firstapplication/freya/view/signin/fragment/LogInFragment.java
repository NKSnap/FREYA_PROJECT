package com.firstapplication.freya.view.signin.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.presenter.signin.SingInPresenter;
import com.firstapplication.freya.view.account.activity.AccountActivity;
import com.firstapplication.freya.view.registration.activity.RegistrationActivity;

import java.util.Objects;
import java.util.Set;


public class LogInFragment extends Fragment implements View.OnClickListener {
    private final SingInPresenter presenter = new SingInPresenter();

    private EditText etEmail, etPassword;
    private boolean valEmail = false;
    private Context context;

    private SharedPreferences sharedPreferences;
    public static final String APP_PREFERENCES = "savedInf";
    public static final String APP_PREFERENCES_EMAIL = "EMAIL";
    public static final String APP_PREFERENCES_PASSWORD = "PASSWORD";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Bundle args = this.getArguments();
        if (args == null) {
            String email = sharedPreferences.getString(APP_PREFERENCES_EMAIL, "");
            String password = sharedPreferences.getString(APP_PREFERENCES_PASSWORD, "");

            if (!email.equals("") && !password.equals(""))
                toOldLogin(presenter.getUserObject(sharedPreferences));
        }

        etEmail = view.findViewById(R.id.et_log_email);
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valEmail = presenter.emailValidator(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword = view.findViewById(R.id.et_log_password);

        view.findViewById(R.id.btn_not_registered).setOnClickListener(this);
        view.findViewById(R.id.btn_login).setOnClickListener(this);
        view.findViewById(R.id.btn_forgot_password).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.DBValidate();
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
                if (loginDataValidator(password)) {
                    RegistrationData registrationData = new RegistrationData();
                    registrationData.setEmail(email);
                    registrationData.setEncryptPassword(password);

                    loginListener(presenter.toLogin(registrationData));
                }
                break;
        }
    }

    private void changePassword() {
        // Change password of this User
    }

    private boolean loginDataValidator(String password) {
        if (!valEmail) {
            Toast.makeText(context, "Почта введена некорректно", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
            return false;
        } else if (password.length() < 7) {
            Toast.makeText(context, "Пароль введен некорректно", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
            return false;
        }

        return true;
    }

    private void registerNewAccount() {
        etEmail.setText("");
        etPassword.setText("");

        Intent intent = new Intent(context, RegistrationActivity.class);
        startActivity(intent);
    }

    private void loginListener(RegistrationData rd) {
        if (rd == null) {
            Toast.makeText(context, "Пользователь не зарегестрирован", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
        } else {
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            presenter.saveNewUser(rd, editor);

            Intent intent = new Intent(context, AccountActivity.class);
            intent.putExtra("user", rd);
            startActivity(intent);
            Toast.makeText(context, "Данные введены верно", Toast.LENGTH_SHORT).show();
            Objects.requireNonNull(getActivity()).finish();
        }
    }

    private void toOldLogin(RegistrationData data) {
        if (data != null) {
            Intent intent = new Intent(context, AccountActivity.class);
            intent.putExtra("user", data);
            startActivity(intent);
            Objects.requireNonNull(getActivity()).finish();
        }
    }

}