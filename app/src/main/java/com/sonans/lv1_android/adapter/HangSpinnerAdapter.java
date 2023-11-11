package com.sonans.lv1_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sonans.lv1_android.R;
import com.sonans.lv1_android.model.Hang;

import java.util.ArrayList;

public class HangSpinnerAdapter extends ArrayAdapter<Hang> {
    Context context;
    ArrayList<Hang> lists;
    TextView tvMa, tvTen;

    public HangSpinnerAdapter(@NonNull Context context, ArrayList<Hang> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (v == null) {
            v = inflater.inflate(R.layout.spinner_hang, parent, false);
        }
        final Hang item = lists.get(position);
        if(item != null){
            tvMa = v.findViewById(R.id.tvMaHangSp);
            tvMa.setText(item.getMaHang()+". ");
            tvTen = v.findViewById(R.id.tvtenHangSp);
            tvTen.setText(item.getTenHang());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (v == null) {
            v = inflater.inflate(R.layout.spinner_hang, parent, false);
        }
        final Hang item = lists.get(position);
        if(item != null){
            tvMa = v.findViewById(R.id.tvMaHangSp);
            tvMa.setText(item.getMaHang()+". ");
            tvTen = v.findViewById(R.id.tvtenHangSp);
            tvTen.setText(item.getTenHang());
        }
        return v;
    }
}
