package com.firstapplication.freya.view.haircut.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.haircut.DateAndTimeSaver;
import com.firstapplication.freya.presenter.haircut.HaircutPresenter;
import com.firstapplication.freya.presenter.registration.RegistrationData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;


public class HaircutInformationFragment extends Fragment implements View.OnClickListener {
    private final HaircutPresenter presenter = new HaircutPresenter();

    private Context context;
    private EditText etSurname, etName, etPatronymic, etNumber;
    private Spinner spinner;
    private RegistrationData userData = null;
    private RadioButton radioButton;

    private HashMap<String, Boolean> time;
    String[] str = {"Не выбрано", "23.05", "24.05"};

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

        return inflater.inflate(R.layout.fragment_haircut_information, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Runnable runnable = () -> {
            String[] times = getResources().getStringArray(R.array.times);
            time = new HashMap<>();
            for (String s : times)
                time.put(s, false);
        };
        Thread thread = new Thread(runnable);
        thread.start();

        etSurname = view.findViewById(R.id.et_haircut_surname);
        etSurname.setText("  " + userData.getSurname());
        etSurname.setEnabled(false);

        etName = view.findViewById(R.id.et_haircut_name);
        etName.setText("  " + userData.getName());
        etName.setEnabled(false);

        etPatronymic = view.findViewById(R.id.et_haircut_patronymic);
        etPatronymic.setText("  " + userData.getPatronymic());
        etPatronymic.setEnabled(false);

        etNumber = view.findViewById(R.id.et_haircut_number);
        etNumber.setText("  " + userData.getNumber());
        etNumber.setEnabled(false);

        setDefaultHaircutType(view);

        spinner = view.findViewById(R.id.spinner_date);
        ArrayAdapter<String> adapterDate = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, str);
        adapterDate.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapterDate);

        view.findViewById(R.id.btn_book_haircut).setOnClickListener(this);
    }

    private void setDefaultHaircutType(View view) {
        if (userData.getGender() == 0)
            radioButton = view.findViewById(R.id.radio_btn_male_haircut);
        else
            radioButton = view.findViewById(R.id.radio_btn_female_haircut);

        radioButton.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_book_haircut) {
//            presenter.del(new DateAndTimeSaver("27:05:2022", time));
//            presenter.writeToFirebase(new DateAndTimeSaver("26:05:2022", time));
//            presenter.writeToFirebase(new DateAndTimeSaver("27:05:2022", time));
        }
    }
}