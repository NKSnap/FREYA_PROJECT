package com.firstapplication.freya.presenter.registration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.firstapplication.freya.repository.registration.RegistrationDBRepository;
import com.firstapplication.freya.repository.registration.RegistrationDBRepositoryImpl;
import com.firstapplication.freya.repository.registration.SQLiteRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RegistrationPresenter {
    private final RegistrationDBRepository repository = new RegistrationDBRepositoryImpl();
    private SQLiteRepository sqLiteRepository;
    private final String[] mails = {"@mail.ru", "@gmail.com", "@yandex.ru"};

    public int toRegister(RegistrationData registrationData) {
        String email = registrationData.getEmail();
        String number = registrationData.getNumber();

        ArrayList<RegistrationData> data = repository.getData();
        boolean flag = true;
        for (RegistrationData rd : data)
            if (rd.getNumber().equals(number) || rd.getEmail().equals(email)) {
                Log.d("VALUES", "found");
                flag = false;
                break;
            }

        if (flag) {
            if (repository.writeToDB(registrationData))
                return 1;
            return -1;
        }
        return 0;
    }

    public void DBValidate(){
        repository.readFromDB();
    }

    public boolean emailValidator(CharSequence email) {
        if (email.length() <= 10)
            return false;

        for (String e : mails)
            if ((email.toString()).contains(e))
                return true;

        return false;
    }

    public boolean passwordValidator(CharSequence password) {
        String pass = String.valueOf(password);
        return pass.matches("((?=.*[a-z])(?=.*[A-Z]).{4,20})");
    }

    public boolean numberValidator(CharSequence number) {
        if (number.length() <= 4) return false;

        String n = String.valueOf(number)
                .replaceAll("[-( )]", "")
                .substring(4);

        return n.length() == 9;
    }

    public boolean dateValidator(String date) {
        long tenYears = 315576000000L;

        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date docDate = format.parse(date);

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

    public long writeToSQLite(Context context, RegistrationData data) {
        sqLiteRepository = new SQLiteRepository(context);

        sqLiteRepository.openToWrite();
        long res = sqLiteRepository.insertToDB(data);
        sqLiteRepository.close();

        return res;
    }

    public void deleteFromFirebase(RegistrationData registrationData) {
        repository.deleteFromDB(registrationData);
    }
}
