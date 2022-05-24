package com.firstapplication.freya.repository.signin;


import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.repository.registration.RegistrationDBRepository;
import com.firstapplication.freya.repository.registration.RegistrationDBRepositoryImpl;

import java.util.ArrayList;

public class SignInDBRepositoryImpl implements SignInDBRepository {
    private final RegistrationDBRepository repository = new RegistrationDBRepositoryImpl();

    @Override
    public void readDataFromDB() {
        repository.readFromDB();
    }

    @Override
    public ArrayList<RegistrationData> getData() {
        return repository.getData();
    }
}
