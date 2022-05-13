package com.firstapplication.freya.repository.registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.firstapplication.freya.presenter.registration.RegistrationData;


public class SQLiteAdapter {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public SQLiteAdapter(Context context) {
        dbHelper = new DBHelper(context.getApplicationContext());
    }

    public SQLiteAdapter openToWrite(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public long insertToDB(RegistrationData data) {
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.COLUMN_EMAIL, data.getEmail());
        cv.put(DBHelper.COLUMN_NUMBER, data.getNumber());
        cv.put(DBHelper.COLUMN_PASSWORD, data.getPassword());
        cv.put(DBHelper.COLUMN_NAME, data.getName());
        cv.put(DBHelper.COLUMN_SURNAME, data.getSurname());
        cv.put(DBHelper.COLUMN_PATRONYMIC, data.getPatronymic());
        cv.put(DBHelper.COLUMN_DATE, data.getDate());
        cv.put(DBHelper.COLUMN_GENDER, data.getGender());

        return database.insert("personal_data", null, cv);
    }

}
