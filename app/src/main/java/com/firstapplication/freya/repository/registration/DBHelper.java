package com.firstapplication.freya.repository.registration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userinfo.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "personal_data";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_PATRONYMIC = "patronymic";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_SURNAME + " TEXT, "
                + COLUMN_PATRONYMIC + " TEXT, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_GENDER + " INTEGER, "
                + COLUMN_EMAIL + " TEXT UNIQUE, "
                + COLUMN_NUMBER + " TEXT UNIQUE, "
                + COLUMN_PASSWORD +  " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
    }
}
