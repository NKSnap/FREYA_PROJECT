package com.firstapplication.freya.view.signin.fragment;

import android.content.Context;
import android.content.Intent;
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


public class LogInFragment extends Fragment implements View.OnClickListener {
    private EditText etEmail, etPassword;
    private Context context;
    private SingInPresenter presenter;

    private final static int ERROR = -1;
    private final static int LOGIN = 1;
    private final static int NOT_CORRECT = 0;
    private final String TAG_ERROR = "ERROR";

    private boolean valEmail = false;

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

                    Log.d(TAG_ERROR, "-------   " + registrationData.getPassword());

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
        // Open activity for registration
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
            Intent intent = new Intent(context, AccountActivity.class);
            intent.putExtra("user", rd);
            startActivity(intent);
            Toast.makeText(context, "Данные введены верно", Toast.LENGTH_SHORT).show();
        }
//        switch (key) {
//            case NOT_CORRECT:
//                Toast.makeText(context, "Пользователь не зарегестрирован", Toast.LENGTH_SHORT).show();
//                etPassword.setText("");
//                break;
//            case LOGIN:
//                // Open Account Activity
//                Intent intent = new Intent(context, AccountActivity.class);
//                startActivity(intent);
//                Toast.makeText(context, "Данные введены верно", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                Log.d(TAG_ERROR, "Ошибка в возвращенных данных при " +
//                        "поиске зарегестрированного пользователя " +
//                        "(Функция presenter.toLogin(registrationData))");
//        }
    }

}