package com.firstapplication.freya.repository.haircut;

import com.firstapplication.freya.presenter.haircut.DateAndTimeSaver;

import java.util.ArrayList;


public interface RealtimeDBRepository {
    ArrayList<DateAndTimeSaver> getSavers();
    boolean writeTimeAndDateToDB(DateAndTimeSaver saver);
    void readTimeAndDateFromDB();
    void changeActiveToPassive(DateAndTimeSaver dateAndTimeSaver);
    ArrayList<String> getDateAndTime();
}
