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

    public int toLogin(RegistrationData registrationData) {
        ArrayList<RegistrationData> data = repository.getData();
        String userEmail = registrationData.getEmail();
        String userPassword = registrationData.getPassword();

        for (RegistrationData rd : data)
            if (userEmail.equals(rd.getEmail()) && userPassword.equals(rd.getPassword()))
                return 1;

        return 0;
    }

}
