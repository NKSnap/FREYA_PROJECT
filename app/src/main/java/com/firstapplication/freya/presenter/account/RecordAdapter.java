package com.firstapplication.freya.presenter.account;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firstapplication.freya.R;

import java.util.List;


public class RecordAdapter extends ArrayAdapter<Record> {
    private final LayoutInflater inflater;
    private final int layout;
    private final List<Record> records;

    public RecordAdapter(Context context, int resource, List<Record> records) {
        super(context, resource, records);
        this.records = records;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = inflater.inflate(this.layout, parent, false);
        Record record = records.get(position);

        ImageView imageIsActive = view.findViewById(R.id.img_is_active);
        TextView textHaircut = view.findViewById(R.id.tw_haircut);
        TextView textDateAndTime = view.findViewById(R.id.tw_date_and_time);

        imageIsActive.setImageResource(record.getIsActive());
        textHaircut.setText(record.getHaircut());
        textDateAndTime.setText(record.getDateAndTime());

        return view;
    }
}
