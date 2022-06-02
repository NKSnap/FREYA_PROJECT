package com.firstapplication.freya.view.haircut.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.haircut.HaircutPresenter;
import com.firstapplication.freya.presenter.registration.RegistrationData;

import java.util.Objects;


public class HaircutInformationFragment extends Fragment implements View.OnClickListener {
    private final HaircutPresenter presenter = new HaircutPresenter();

    private Context context;
    private EditText etSurname, etName, etPatronymic, etNumber;
    private Spinner spinnerDate, spinnerTime, spinnerAddress;
    private RegistrationData userData = null;
    private RadioButton radioButton;

    private final AdapterView.OnItemSelectedListener dateListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String date = (String) parent.getItemAtPosition(position);
            setSpinner(spinnerTime, null, date);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    };

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
            userData = (RegistrationData) args.getSerializable("user");
        } else {
            Log.d("ERROR", "Ошибка передачи данных во фрагмент AccountDataFragment");
            Objects.requireNonNull(getActivity()).finish();
        }

        return inflater.inflate(R.layout.fragment_haircut_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSurname = view.findViewById(R.id.et_haircut_surname);
        setETText(etSurname, userData.getSurname());

        etName = view.findViewById(R.id.et_haircut_name);
        setETText(etName, userData.getName());

        etPatronymic = view.findViewById(R.id.et_haircut_patronymic);
        setETText(etPatronymic, userData.getPatronymic());

        etNumber = view.findViewById(R.id.et_haircut_number);
        setETText(etNumber, userData.getNumber());

        setDefaultHaircutType(view);

        view.findViewById(R.id.btn_book_haircut).setOnClickListener(this);

        String[] dates = presenter.getDates();
        spinnerDate = view.findViewById(R.id.spinner_date);
        spinnerDate.setOnItemSelectedListener(dateListener);
        setSpinner(spinnerDate, dates, null);

        spinnerTime = view.findViewById(R.id.spinner_time);
        setSpinner(spinnerTime, null, dates[0]);

        spinnerAddress = view.findViewById(R.id.spinner_address);
        String[] address = getResources().getStringArray(R.array.addresses);
        setSpinner(spinnerAddress, address, null);
    }

    @SuppressLint("SetTextI18n")
    private void setETText(EditText editText, String text){
        editText.setText("  " + text);
        editText.setEnabled(false);
    }

    private void setSpinner(Spinner spinner, String[] data, String date) {
        if (date != null)
            data = presenter.getTime(date);
        ArrayAdapter<String> adapterDate = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, data);
        adapterDate.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapterDate);
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
        if (v.getId() == R.id.btn_book_haircut)
            Toast.makeText(context, "Запись пока не доступна", Toast.LENGTH_SHORT).show();
    }
}