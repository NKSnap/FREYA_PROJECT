package com.firstapplication.freya.repository.haircut;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.firstapplication.freya.presenter.haircut.DateAndTimeSaver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;


public class RealtimeDBRepositoryImpl implements RealtimeDBRepository {
    private static RealtimeDBRepositoryImpl repository = null;

    private final String TAG_ERROR = "ERROR";
    private final String dbMainChild = "haircuts";
    private final String dbChildActiveDates = "active_dates";
    private final String dbChildPassiveDates = "passive_dates";

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference dbRef = database.getReference(dbMainChild);

    private final ArrayList<DateAndTimeSaver> savers = new ArrayList<>();

    private RealtimeDBRepositoryImpl() {
    }

    public static RealtimeDBRepositoryImpl getRepository() {
        if (repository == null)
            repository = new RealtimeDBRepositoryImpl();
        return repository;
    }

    @Override
    public ArrayList<DateAndTimeSaver> getSavers() {
        return savers;
    }

    @Override
    public boolean writeTimeAndDateToDB(DateAndTimeSaver saver) {
        try {
            dbRef.child(dbChildActiveDates).child(saver.getDate()).setValue(saver);
        } catch (Exception exception) {
            Log.d(TAG_ERROR, "Ошибка добавления пользователя в Realtime Database.\n"
                    + exception.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public void readTimeAndDateFromDB() {
        if (savers.size() > 0)
            savers.clear();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd:MM:yyyy");

        dbRef.child(dbChildActiveDates).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    DateAndTimeSaver o = s.getValue(DateAndTimeSaver.class);
                    String date = Objects.requireNonNull(o).getDate();

                    Calendar thisCalendar = Calendar.getInstance();
                    Calendar nowCalendar = new GregorianCalendar();

                    try {
                        Date d = format.parse(date);
                        thisCalendar.setTime(Objects.requireNonNull(d));
                    } catch (ParseException e) {
                        Log.d(TAG_ERROR, "ERROR WITH A DATE" + e.getMessage());
                    }

                    setSavers(o, nowCalendar, thisCalendar);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG_ERROR, "Ошибка при чтении данных из Realtime Database.", error.toException());
            }
        });
    }

    @Override
    public void changeActiveToPassive(DateAndTimeSaver dateAndTimeSaver) {
        Query query = dbRef.child(dbChildActiveDates).orderByChild("date").equalTo(dateAndTimeSaver.getDate());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    s.getRef().removeValue();

                    DateAndTimeSaver newSaver = s.getValue(DateAndTimeSaver.class);
                    assert newSaver != null;
                    dbRef.child(dbChildPassiveDates).child(newSaver.getDate()).setValue(newSaver);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    @Override
    public ArrayList<String> getDateAndTime() {
        ArrayList<String> dates = new ArrayList<>();
        for (DateAndTimeSaver saver : savers)
            dates.add(saver.getDate());
        return dates;
    }

    private void setSavers(DateAndTimeSaver saver, Calendar nowC, Calendar thisC) {
        if (thisC.after(nowC))
            savers.add(saver);
        else
            changeActiveToPassive(saver);
    }
}