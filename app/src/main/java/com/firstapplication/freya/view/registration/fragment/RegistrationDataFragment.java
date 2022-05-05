package com.firstapplication.freya.view.registration.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.registration.RegistrationData;


public class RegistrationDataFragment extends Fragment {
    Context context;

    EditText etEmail, etNumber, etPassword;

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
        etNumber = view.findViewById(R.id.et_reg_number);
        etPassword = view.findViewById(R.id.et_reg_password);
    }

    public void getData(RegistrationData registrationData) {
        Toast.makeText(context, "Everything is fine!", Toast.LENGTH_SHORT).show();
    }

}