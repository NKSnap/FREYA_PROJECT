package com.firstapplication.freya.presenter.haircut;


import com.firstapplication.freya.repository.haircut.RealtimeDBRepositoryImpl;

public class HaircutPresenter {
    private final RealtimeDBRepositoryImpl repository = new RealtimeDBRepositoryImpl();

    public int writeToFirebase(DateAndTimeSaver saver) {
        if (repository.writeTimeAndDateToDB(saver))
            return 0;
        return 1;
    }

    public void del(DateAndTimeSaver saver) {
        repository.changeActiveToPassive(saver);
    }

}
