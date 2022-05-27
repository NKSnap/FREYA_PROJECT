package com.firstapplication.freya.view.account.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firstapplication.freya.R;
import com.firstapplication.freya.presenter.account.AccountPresenter;
import com.firstapplication.freya.presenter.account.Record;
import com.firstapplication.freya.presenter.account.RecordAdapter;
import com.firstapplication.freya.presenter.registration.RegistrationData;
import com.firstapplication.freya.view.haircut.activity.HaircutActivity;

import java.util.ArrayList;
import java.util.Objects;


public class RecordsListFragment extends Fragment implements View.OnClickListener{
    private final AccountPresenter presenter = new AccountPresenter();
    private Context context;

    private ListView recordsList;
    private ArrayList<Record> records;

    private RegistrationData userData = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = this.getArguments();
        if (args != null) {
            Log.d("ERROR", "Получение данных");
            userData = (RegistrationData) args.getSerializable("user");
        } else {
            Log.d("ERROR", "Ошибка передачи данных во фрагмент AccountDataFragment");
            Objects.requireNonNull(getActivity()).finish();
        }

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

        view.findViewById(R.id.btn_make_record).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_make_record) {
                Intent intent = new Intent(context, HaircutActivity.class);
                intent.putExtra("user", userData);
                startActivity(intent);
        }
    }
}











