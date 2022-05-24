package com.firstapplication.freya.presenter.account;

import android.content.Context;
import android.util.Log;

import com.firstapplication.freya.repository.account.SQLiteRepository;
import com.firstapplication.freya.repository.account.SQLiteRepositoryImpl;

import java.util.ArrayList;

public class AccountPresenter {
    SQLiteRepository repository = null;

    public void initDB(Context context) {
        repository = new SQLiteRepositoryImpl(context.getApplicationContext());
    }

    public ArrayList<Record> getRecords() {
        if (repository == null) {
            notifyInitializeError();
            return null;
        }

        repository.open();
        ArrayList<Record> records = (ArrayList<Record>) repository.readFromDB();
        repository.close();
        return records;
    }

    public long makeRecord(Record record) {
        if (repository == null)
            notifyInitializeError();

        repository.open();
        long key = repository.insertToDB(record);
        repository.close();
        return key;
    }

    private void notifyInitializeError() {
        Log.d("ERROR", "Не инициализирована база данных для списка зписей");
    }

}
