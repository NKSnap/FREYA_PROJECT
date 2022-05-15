package com.firstapplication.freya.repository.registration;

import com.firstapplication.freya.presenter.registration.RegistrationData;

import java.util.ArrayList;

public interface RegistrationDBRepository {
    boolean writeToDB(RegistrationData registrationData);
    void deleteFromDB(RegistrationData registrationData);
    void readFromDB();
    ArrayList<RegistrationData> getData();
}
