package com.firstapplication.freya.presenter.haircut;

import android.annotation.SuppressLint;
import android.util.Log;

import com.firstapplication.freya.repository.haircut.RealtimeDBRepositoryImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;


public class HaircutPresenter {
    private final RealtimeDBRepositoryImpl repository = RealtimeDBRepositoryImpl.getRepository();

    private final String[] str = {"9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00"};
    private HashMap<String, Boolean> time;
    private final ArrayList<DateAndTimeSaver> savers = repository.getSavers();

    public HaircutPresenter() {
        setDefaultTime();
    }

    public void setDefaultTime() {
        this.time = new HashMap<>();
        for (String s : str)
            this.time.put(s, false);
    }

    public int writeToFirebase(DateAndTimeSaver saver) {
        if (repository.writeTimeAndDateToDB(saver))
            return 0;
        return 1;
    }

    public String[] getTime(String date) {
        Map<String, Boolean> object = null;
        for (DateAndTimeSaver saver : savers)
            if (saver.getDate().equals(date)) {
                object = saver.getTimes();
                break;
            }

        if (object == null)
            return new String[]{"Нет свободного времени"};

        SortedSet<String> keys = new TreeSet<>(object.keySet());
        String[] time = new String[object.size()];
        int i = 0;
        for (String key : keys) {
            if (!object.get(key))
                time[i] = key;

            i++;
        }

        return time;
    }

    private ArrayList<String> setDatesList(ArrayList<String> dates, SimpleDateFormat format) {
        while (dates.size() < 5) {
            String date = dates.get(dates.size() - 1);
            Calendar calendar = Calendar.getInstance();

            try {
                Date parse = format.parse(date);
                calendar.setTime(Objects.requireNonNull(parse));
                calendar.add(Calendar.DAY_OF_YEAR, 1);

                date = format.format(calendar.getTime());

                dates.add(date);
                savers.add(new DateAndTimeSaver(date, time));
            } catch (ParseException e) {
                Log.d("ERROR", "ERROR WITH A DATE " + e.getMessage());
            }
        }

        return dates;
    }

    private String[] getDateMassiveFromList(ArrayList<String> dates) {
        String[] dateMassive = new String[dates.size()];
        for (int i = 0; i < dates.size(); i++)
            dateMassive[i] = dates.get(i);

        return dateMassive;
    }

    public String[] getDates() {
        ArrayList<String> dates = repository.getDateAndTime();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd:MM:yyyy");

        if (dates.size() == 0) {
            Calendar nowTime = new GregorianCalendar();
            nowTime.add(Calendar.DAY_OF_YEAR, 1);
            String dat = format.format(nowTime.getTime());

            dates.add(dat);
            savers.add(new DateAndTimeSaver(dat, time));
        }

        dates = setDatesList(dates, format);
        return getDateMassiveFromList(dates);
    }

    public void readDateAndTime() {
        repository.readTimeAndDateFromDB();
    }

}
