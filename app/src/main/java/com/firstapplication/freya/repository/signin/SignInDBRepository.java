package com.firstapplication.freya.repository.signin;

import com.firstapplication.freya.presenter.registration.RegistrationData;

import java.util.ArrayList;

public interface SignInDBRepository {
    void readDataFromDB();
    ArrayList<RegistrationData> getData();
}
