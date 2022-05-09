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


public class RegistrationDataFragment extends Fragment {
    private final RegistrationPresenter presenter = new RegistrationPresenter();
    private Context context;

    private EditText etEmail, etNumber, etPassword;
    private boolean valEmail = false;
    private boolean valNumber = false;
    private boolean valPass = false;

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (presenter.emailValidator(s)) {
                    valEmail = true;
                    Toast.makeText(context, "True", Toast.LENGTH_SHORT).show();
                } else if (valEmail)
                    valEmail = false;
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        etNumber = view.findViewById(R.id.et_reg_number);
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 4) return;

                String number = String.valueOf(s)
                        .replaceAll("[-( )]", "")
                        .substring(4);

                if (number.length() == 9) {
                    valNumber = true;
                    Toast.makeText(context, "True", Toast.LENGTH_SHORT).show();
                } else if (valNumber)
                    valNumber = false;
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = String.valueOf(s);
                if (pass.matches("((?=.*d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})")) {
                    valPass = true;
                    Toast.makeText(context, "Password is right", Toast.LENGTH_SHORT).show();
                } else if (valPass)
                    valPass = false;
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public void getData(RegistrationData registrationData) {
        if (valEmail)
            registrationData.setEmail(etEmail.getText().toString());
        else {
            Toast.makeText(context, "Mail isn't correct", Toast.LENGTH_SHORT).show();
            return;    
        }
            
        if (valNumber)
            registrationData.setNumber(etNumber.getText().toString());
        else {
            Toast.makeText(context, "Number isn't correct", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (valPass)
            registrationData.setPassword(etPassword.getText().toString());
        else {
            etPassword.setText("");
            Toast.makeText(context, "Password isn't correct", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(context, "Everything is fine!", Toast.LENGTH_SHORT).show();
    }
}