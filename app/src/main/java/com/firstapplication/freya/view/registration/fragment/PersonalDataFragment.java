package com.firstapplication.freya.view.registration.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.presenter.registration.RegistrationPresenter;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class PersonalDataFragment extends Fragment implements View.OnClickListener {

    public interface DataSender {
        void onDataSend(RegistrationData registrationData);
    }

    private final int MALE = 0;
    private final int FEMALE = 1;
    private final int ERROR = -1;

    private final RegistrationPresenter presenter = new RegistrationPresenter();
    private DataSender dataSender;

    private Button date;
    private EditText etSurname, etName, etPatronymic;

    private Context context;

    private int gender = ERROR;
    private final Calendar dateAndTime = new GregorianCalendar();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        try {
            dataSender = (DataSender) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + " interface not implemented!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSurname = view.findViewById(R.id.et_reg_surname);
        etName = view.findViewById(R.id.et_reg_name);
        etPatronymic = view.findViewById(R.id.et_reg_patronymic);

        view.findViewById(R.id.radio_btn_male).setOnClickListener(this);
        view.findViewById(R.id.radio_btn_female).setOnClickListener(this);

        date = view.findViewById(R.id.btn_reg_date);
        date.setText(presenter.setDate(null));
        date.setOnClickListener(this);

        view.findViewById(R.id.btn_reg_register).setOnClickListener(this);
    }

    private void getGender(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case (int) R.id.radio_btn_male:
                if (checked)
                    gender = MALE;
                break;
            case (int) R.id.radio_btn_female:
                if (checked)
                    gender = FEMALE;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (int) R.id.btn_reg_register:
                String surname = etSurname.getText().toString();
                String name = etName.getText().toString();
                String patronymic = etPatronymic.getText().toString();
                String date = this.date.getText().toString();
                if (gender != ERROR)
                    dataSender.onDataSend(new RegistrationData(
                            surname, name, patronymic, gender, date
                    ));
                break;
            case (int) R.id.btn_reg_date:
                new DatePickerDialog(context, dateListener,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;
            case (int) R.id.radio_btn_male:
            case (int) R.id.radio_btn_female:
                getGender(v);
                break;
        }
    }

    private final DatePickerDialog.OnDateSetListener dateListener = (view, year, month, dayOfMonth) -> {
        dateAndTime.set(Calendar.YEAR, year);
        dateAndTime.set(Calendar.MONTH, month);
        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date.setText(presenter.setDate(dateAndTime));
    };

}