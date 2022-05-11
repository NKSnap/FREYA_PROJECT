package com.firstapplication.freya.repository.registration;

import android.util.Log;

import androidx.annotation.NonNull;

import com.firstapplication.freya.presenter.registration.FirebaseFormat;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RegistrationDBRepositoryImpl implements RegistrationDBRepository {
    private final String TAG_ERROR = "ERROR";
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference dbRef = database.getReference().child("login");

    @Override
    public boolean checkUsers(String email, String number) {
        ArrayList<FirebaseFormat> arrayList = new ArrayList<>();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                    arrayList.add(dataSnapshot.getValue(FirebaseFormat.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG_ERROR, "Ошибка в считывании даннных из базы данных Firebase.\n"
                        + error.getMessage());
            }
        });

        for (FirebaseFormat firebaseFormat : arrayList) {
            if (email.equals(firebaseFormat.getEmail()) || number.equals(firebaseFormat.getNumber()))
                return false;
        }

        return true;
    }

    @Override
    public boolean writeToDB(RegistrationData registrationData){
        FirebaseFormat firebaseFormat = registrationData.toFirebaseFormat();

        try {
            dbRef.child(firebaseFormat.getFID()).setValue(registrationData);
        } catch (Exception exception) {
            Log.d(TAG_ERROR, "Ошибка добавления пользователя в Realtime Database.\n"
                    + exception.getMessage());
            return false;
        }

        return true;
    }
}
