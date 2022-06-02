package com.firstapplication.freya.presenter.haircut;

import java.io.Serializable;
import java.util.HashMap;


public class DateAndTimeSaver implements Serializable {
    private String date;
    private HashMap<String, Boolean> times;

    public DateAndTimeSaver(String date, HashMap<String, Boolean> times) {
        this.date = date;
        this.times = times;
    }

    public DateAndTimeSaver() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap<String, Boolean> getTimes() {
        return times;
    }

    public void setTimes(HashMap<String, Boolean> times) {
        this.times = times;
    }
}
