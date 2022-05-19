package com.firstapplication.freya.presenter.signin;

import android.content.Context;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.repository.signin.SignInDBRepository;
import com.firstapplication.freya.repository.signin.SignInDBRepositoryImpl;
import java.util.ArrayList;


public class SingInPresenter {
    private final SignInDBRepository repository = new SignInDBRepositoryImpl();
    Context context;
    private final String[] mails = {"@mail.ru", "@gmail.com", "@yandex.ru"};

    public SingInPresenter(Context context) {
        this.context = context;
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
