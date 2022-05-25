package com.firstapplication.freya.view.haircut.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.firstapplication.freya.R;


public class HaircutInformationFragment extends Fragment {
    private Context context;
    private EditText etSurname, etName, etPatronymic, etNumber;
    private Spinner spinner;

    String[] str = {"Не выбрано", "23.05", "24.05"};

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_haircut_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSurname = view.findViewById(R.id.et_haircut_surname);
        etSurname.setEnabled(false);

        etName = view.findViewById(R.id.et_haircut_name);
        etName.setEnabled(false);

        etPatronymic = view.findViewById(R.id.et_haircut_patronymic);
        etPatronymic.setEnabled(false);

        etNumber = view.findViewById(R.id.et_haircut_number);
        etNumber.setEnabled(false);

        spinner = view.findViewById(R.id.spinner_date);
        ArrayAdapter<String> adapterDate = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, str);
        // Определяем разметку для использования при выборе элемента
        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapterDate);
    }
}