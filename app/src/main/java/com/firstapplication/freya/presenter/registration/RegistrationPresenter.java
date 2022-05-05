package com.firstapplication.freya.presenter.registration;

import com.firstapplication.freya.repository.registration.RegistrationDBRepository;
import com.firstapplication.freya.repository.registration.RegistrationDBRepositoryImpl;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegistrationPresenter {
    private final RegistrationDBRepository repository = new RegistrationDBRepositoryImpl();

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
