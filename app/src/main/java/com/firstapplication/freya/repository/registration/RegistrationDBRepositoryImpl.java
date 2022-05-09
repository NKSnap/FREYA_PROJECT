package com.firstapplication.freya.repository.registration;

import android.util.Log;

import androidx.annotation.NonNull;

import com.firstapplication.freya.presenter.registration.DBFormat;
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
    public boolean userCheck(String email) {
        ArrayList<DBFormat> arrayList = new ArrayList<>();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                    arrayList.add(dataSnapshot.getValue(DBFormat.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG_ERROR, "Ошибка в считывании даннных из базы данных Firebase");
            }
        });

        for (DBFormat dbFormat : arrayList) {
            if (email.equals(dbFormat.getEmail()))
                return false;
        }

        return true;
    }
}
