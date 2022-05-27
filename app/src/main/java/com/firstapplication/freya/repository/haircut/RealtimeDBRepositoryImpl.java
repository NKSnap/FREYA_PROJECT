package com.firstapplication.freya.repository.haircut;

import android.util.Log;

import androidx.annotation.NonNull;

import com.firstapplication.freya.presenter.haircut.DateAndTimeSaver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RealtimeDBRepositoryImpl {
    private final String TAG_ERROR = "ERROR";
    private final String dbMainChild = "haircuts";
    private final String dbChildActiveDates = "active_dates";
    private final String dbChildPassiveDates = "passive_dates";

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference dbRef = database.getReference(dbMainChild);

    private final ArrayList<DateAndTimeSaver> savers = new ArrayList<>();

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

    public void readTimeAndDateFromDB() {
        if (savers.size() > 0)
            savers.clear();

        dbRef.child(dbChildActiveDates).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren())
                    savers.add(s.getValue(DateAndTimeSaver.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG_ERROR, "Ошибка при чтении данных из Realtime Database.", error.toException());
            }
        });
    }

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
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public ArrayList<DateAndTimeSaver> getDateAndTime() {
        return savers;
    }
}
