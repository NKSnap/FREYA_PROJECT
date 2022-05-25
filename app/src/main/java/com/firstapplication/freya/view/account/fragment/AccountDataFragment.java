package com.firstapplication.freya.view.account.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.registration.RegistrationData;

import java.util.Objects;


public class AccountDataFragment extends Fragment {
    private Context context;
    private RegistrationData userData = null;
    private TextView twSurname, twName, twPatronymic;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = this.getArguments();
        if (args != null) {
            Log.d("ERROR", "Получение данных");
            userData = (RegistrationData) args.getSerializable("user");
        } else {
            Log.d("ERROR", "Ошибка передачи данных во фрагмент AccountDataFragment");
            Objects.requireNonNull(getActivity()).finish();
        }

        return inflater.inflate(R.layout.fragment_account_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        twName = view.findViewById(R.id.tw_name);
        twName.setText(userData.getName());

        twSurname = view.findViewById(R.id.tw_surname);
        twSurname.setText(userData.getSurname());

        twPatronymic = view.findViewById(R.id.tw_patronymic);
        twPatronymic.setText(userData.getPatronymic());
    }
}