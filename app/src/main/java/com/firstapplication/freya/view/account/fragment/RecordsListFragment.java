package com.firstapplication.freya.view.account.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.account.AccountPresenter;
import com.firstapplication.freya.presenter.account.Record;
import com.firstapplication.freya.presenter.account.RecordAdapter;

import java.util.ArrayList;


public class RecordsListFragment extends Fragment {
    private final AccountPresenter presenter = new AccountPresenter();
    private Context context;

    private ListView recordsList;
    private ArrayList<Record> records;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_records_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.initDB(context);
        records = presenter.getRecords();

        recordsList = view.findViewById(R.id.list_records);
        RecordAdapter recordAdapter = new RecordAdapter(context, R.layout.list_records, records);
        recordsList.setAdapter(recordAdapter);
    }
}











