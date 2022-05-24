package com.firstapplication.freya.presenter.signin;

import android.content.SharedPreferences;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.repository.signin.SignInDBRepository;
import com.firstapplication.freya.repository.signin.SignInDBRepositoryImpl;
import java.util.ArrayList;


public class SingInPresenter {
    private static final String EMAIL = "EMAIL";
    private static final String NUMBER = "NUMBER";
    private static final String PASSWORD = "PASSWORD";
    private static final String GENDER = "GENDER";
    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final String PATRONYMIC = "PATRONYMIC";
    private static final String DATE = "DATE";

    private final SignInDBRepository repository = new SignInDBRepositoryImpl();
    private final String[] mails = {"@mail.ru", "@gmail.com", "@yandex.ru"};

    public void saveNewUser(RegistrationData data, SharedPreferences.Editor editor) {
        editor.putString(EMAIL, data.getEmail());
        editor.putString(NUMBER, data.getNumber());
        editor.putString(PASSWORD, data.getPassword());
        editor.putString(GENDER, String.valueOf(data.getGender()));
        editor.putString(NAME, data.getName());
        editor.putString(SURNAME, data.getSurname());
        editor.putString(PATRONYMIC, data.getPatronymic());
        editor.putString(DATE, data.getDate());
        editor.apply();
    }

    public RegistrationData getUserObject(SharedPreferences sharedPreferences) {
        RegistrationData data = new RegistrationData();

        data.setEmail(sharedPreferences.getString(EMAIL, ""));
        data.setPassword(sharedPreferences.getString(PASSWORD, ""));
        data.setNumber(sharedPreferences.getString(NUMBER, ""));
        data.setDate(sharedPreferences.getString(DATE, ""));
        data.setName(sharedPreferences.getString(NAME, ""));
        data.setSurname(sharedPreferences.getString(SURNAME, ""));
        data.setPatronymic(sharedPreferences.getString(PATRONYMIC, ""));
        data.setGender(Integer.parseInt(sharedPreferences.getString(GENDER, "")));

        return data;
    }

    public boolean emailValidator(CharSequence email) {
        if (email.length() <= 10)
            return false;

        for (String e : mails)
            if ((email.toString()).contains(e))
                return true;

        return false;
    }

    public void DBValidate() {
        repository.readDataFromDB();
    }

    /*
     * В функции toLogin заменить возврат на объект класса RegistrationData
     * Исправить функцию loginListener(int key) в соответствии с изменения в toLogin
     * Переданный объект класса RegistrationData отправляем в AccountActivity
     * Уже в AccountActivity передаем данные во фрагмент и устанавливает нужные значения
     * */

    public RegistrationData toLogin(RegistrationData registrationData) {
        ArrayList<RegistrationData> data = repository.getData();
        String userEmail = registrationData.getEmail();
        String userPassword = registrationData.getPassword();

        for (RegistrationData rd : data)
            if (userEmail.equals(rd.getEmail()) && userPassword.equals(rd.getPassword()))
                return rd;

        return null;

//        ArrayList<RegistrationData> data = repository.getData();
//        String userEmail = registrationData.getEmail();
//        String userPassword = registrationData.getPassword();
//
//        for (RegistrationData rd : data)
//            if (userEmail.equals(rd.getEmail()) && userPassword.equals(rd.getPassword()))
//                return 1;
//
//        return 0;
    }

}
