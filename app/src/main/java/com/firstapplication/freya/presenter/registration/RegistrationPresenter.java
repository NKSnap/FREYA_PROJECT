package com.firstapplication.freya.presenter.registration;

import android.annotation.SuppressLint;
import android.util.Log;

import com.firstapplication.freya.repository.registration.RegistrationDBRepository;
import com.firstapplication.freya.repository.registration.RegistrationDBRepositoryImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RegistrationPresenter {
    private final RegistrationDBRepository repository = new RegistrationDBRepositoryImpl();
    private final String[] mails = {"@mail.ru", "@gmail.com", "@yandex.ru"};

    public boolean emailValidator (CharSequence email) {
        if (email.length() <= 10)
            return false;

        for (String e : mails) {
            if ((email.toString()).contains(e)) {
                return true;
            }
        }

        return false;
    }

    public boolean passwordValidator (CharSequence password) {
        return false;
    }

    public boolean numberValidator (CharSequence number) {
        return false;
    }

    public boolean surnameValidator (CharSequence surname) {
        return false;
    }

    public boolean nameValidator (CharSequence name) {
        return false;
    }

    public boolean patronymicValidator (CharSequence patronymic) {
        return false;
    }

    public boolean dateValidator (String date) {
        long tenYears = 315576000000L;

        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date docDate= format.parse(date);

            return docDate != null && docDate.getTime() < (new Date().getTime() - tenYears);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String setDate(Calendar calendar) {
        if (calendar == null)
            calendar = new GregorianCalendar();

        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1)
            day = "0" + day;

        String month = String.valueOf((calendar.get(Calendar.MONTH) + 1));
        if (month.length() == 1)
            month = "0" + month;

        return day + "." + month + "." + calendar.get(Calendar.YEAR);
    }
}
