package com.firstapplication.freya.repository.account;

import com.firstapplication.freya.presenter.account.Record;
import java.util.List;


public interface SQLiteRepository {
    SQLiteRepositoryImpl open();
    void close();
    long insertToDB(Record record);
    List<Record> readFromDB();
}
