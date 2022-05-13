package com.firstapplication.freya.repository.registration;

import android.util.Log;

import androidx.annotation.NonNull;

import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class RegistrationDBRepositoryImpl implements RegistrationDBRepository {
    private final String TAG_ERROR = "ERROR";
    private final String dbChild = "login";
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference dbRef = database.getReference().child(dbChild);
    private final ArrayList<RegistrationData> dbData = new ArrayList<>();

    public ArrayList<RegistrationData> getData() {
        return dbData;
    }

    @Override
    public boolean writeToDB(RegistrationData registrationData) {
        try {
            dbRef.child(registrationData.getNumber()).setValue(registrationData);
        } catch (Exception exception) {
            Log.d(TAG_ERROR, "Ошибка добавления пользователя в Realtime Database.\n"
                    + exception.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public void deleteFromDB(RegistrationData registrationData) {
        Query query = dbRef.orderByChild("number").equalTo(registrationData.getNumber());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren())
                    s.getRef().removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public long writeToSQLite(SQLiteAdapter repository, RegistrationData data) {
        repository.openToWrite();
        long res = repository.insertToDB(data);
        repository.close();

        return res;
    }

    @Override
    public void readFromDB() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    dbData.add(s.getValue(RegistrationData.class));
                    Log.d(TAG_ERROR, "Value is: +" +
                            Objects.requireNonNull(s.getValue(RegistrationData.class)).getNumber());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG_ERROR, "Ошибка при чтении данных из Realtime Database.", error.toException());
            }
        });
    }
}
