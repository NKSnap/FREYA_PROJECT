package com.firstapplication.freya.view.registration.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.presenter.registration.RegistrationPresenter;
import java.util.Objects;


public class RegistrationDataFragment extends Fragment {
    private final RegistrationPresenter presenter = new RegistrationPresenter();
    private Context context;

    private EditText etEmail, etNumber, etPassword;
    private boolean valEmail = false;
    private boolean valNumber = false;
    private boolean valPass = false;

    private final int CREATED = 1;
    private final int ALREADY_CREATED = 0;
    private final int ERROR = -1;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etEmail = view.findViewById(R.id.et_reg_email);
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

        etNumber = view.findViewById(R.id.et_reg_number);
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valNumber = presenter.numberValidator(s);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                String number = etNumber.getText().toString();
                if (!number.startsWith("+375"))
                    etNumber.setText("+375");
            }
        });

        etPassword = view.findViewById(R.id.et_reg_password);
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valPass = presenter.passwordValidator(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        presenter.DBValidate();
    }

    public void getData(RegistrationData registrationData) {
        if (valEmail)
            registrationData.setEmail(etEmail.getText().toString());
        else {
            Toast.makeText(context, "Почта введена некорректно", Toast.LENGTH_SHORT).show();
            return;
        }

        if (valNumber)
            registrationData.setNumber(etNumber.getText().toString());
        else {
            Toast.makeText(context, "Номер введен некорректно", Toast.LENGTH_SHORT).show();
            return;
        }

        if (valPass)
            registrationData.setPassword(etPassword.getText().toString());
        else {
            etPassword.setText("");
            Toast.makeText(context, "Пароль введен некорректно", Toast.LENGTH_SHORT).show();
            return;
        }

        int fKey = presenter.toRegister(registrationData);
        if (fKey == CREATED) {
            if (presenter.writeToSQLite(context, registrationData) == -1) {
                presenter.deleteFromFirebase(registrationData);
                Objects.requireNonNull(getActivity()).finish();
                notifyUser("Непредвиденная ошибка!");
                return;
            }

            notifyUser("Аккаунт создан!");
            Objects.requireNonNull(getActivity()).finish();
        } else if (fKey == ALREADY_CREATED) {
            etPassword.setText("");
            notifyUser("Пользователь уже зарегестрирован!");
        } else if (fKey == ERROR) {
            notifyUser("Непредвиденная ошибка!");
            Objects.requireNonNull(getActivity()).finish();
        }
    }

    private void notifyUser(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}