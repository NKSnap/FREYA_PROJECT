package com.firstapplication.freya.repository.account;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.firstapplication.freya.presenter.account.Record;
import com.firstapplication.freya.repository.registration.DBHelper;

import java.util.ArrayList;
import java.util.List;


public class SQLiteRepositoryImpl implements SQLiteRepository{
    private final DBHelper dbHelper;
    private SQLiteDatabase database;

    public SQLiteRepositoryImpl(Context context) {
        dbHelper = new DBHelper(context.getApplicationContext());
    }

    public SQLiteRepositoryImpl open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public long insertToDB(Record record) {
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.COLUMN_HAIRCUT_TYPE, record.getHaircut());
        cv.put(DBHelper.COLUMN_DATE_AND_TIME, record.getDateAndTime());
        cv.put(DBHelper.COLUMN_IMAGE, record.getIsActive());

        return database.insert("records", null, cv);
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {DBHelper.COLUMN_HAIRCUT_TYPE,
                DBHelper.COLUMN_DATE_AND_TIME, DBHelper.COLUMN_IMAGE};
        return  database.query("records", columns, null,
                null, null, null, null);
    }

    public List<Record> readFromDB() {
        ArrayList<Record> records = new ArrayList<>();

        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            @SuppressLint("Range") String haircut = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_HAIRCUT_TYPE));
            @SuppressLint("Range") String dateAndTime = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DATE_AND_TIME));
            @SuppressLint("Range") int imageType = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_IMAGE));
            records.add(new Record(haircut, dateAndTime, imageType));
        }

        cursor.close();
        return records;
    }
}
