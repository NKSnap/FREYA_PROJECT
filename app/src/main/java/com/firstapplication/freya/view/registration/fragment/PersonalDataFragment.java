package com.firstapplication.freya.view.registration.fragment;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.presenter.registration.RegistrationPresenter;
import com.firstapplication.freya.view.registration.activity.RegistrationActivity;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

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
    private final Calendar today = new GregorianCalendar();

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

        view.findViewById(R.id.btn_reg_register).setOnClickListener(this);
        view.findViewById(R.id.radio_btn_male).setOnClickListener(this);
        view.findViewById(R.id.radio_btn_female).setOnClickListener(this);

        date = view.findViewById(R.id.btn_reg_date);
        date.setText(presenter.setDate(null));
        date.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (int) R.id.btn_reg_register:
                String surname = etSurname.getText().toString();
                String name = etName.getText().toString();
                String patronymic = etPatronymic.getText().toString();
                String date = this.date.getText().toString();

                if (gender == ERROR || surname.equals("") || name.equals("") || patronymic.equals(""))
                    Snackbar.make(v, "Enter all data", Snackbar.LENGTH_LONG).show();

                if (!presenter.dateValidator(date))
                    Snackbar.make(v, "You are very young", Snackbar.LENGTH_LONG).show();

                dataSender.onDataSend(new RegistrationData(surname, name,
                        patronymic, gender, date));
                break;
            case (int) R.id.btn_reg_date:
                new DatePickerDialog(context, dateListener,
                        today.get(Calendar.YEAR),
                        today.get(Calendar.MONTH),
                        today.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;
            case (int) R.id.radio_btn_male:
            case (int) R.id.radio_btn_female:
                setGender(v);
                break;
        }
    }

    private final DatePickerDialog.OnDateSetListener dateListener = (view, year, month, dayOfMonth) -> {
        today.set(Calendar.YEAR, year);
        today.set(Calendar.MONTH, month);
        today.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date.setText(presenter.setDate(today));
    };

    private void setGender(View view) {
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
            default:
                gender = ERROR;
        }
    }

}