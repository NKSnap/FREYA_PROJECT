package com.firstapplication.freya.repository.registration;

import com.firstapplication.freya.presenter.registration.RegistrationData;

public interface RegistrationDBRepository {
    boolean checkUsers(String email, String number);
    boolean writeToDB(RegistrationData registrationData);
}
