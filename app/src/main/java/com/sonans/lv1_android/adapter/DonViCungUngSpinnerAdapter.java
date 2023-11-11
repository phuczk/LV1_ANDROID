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
import com.sonans.lv1_android.model.CungUng;
import com.sonans.lv1_android.model.Hang;

import java.util.ArrayList;

public class DonViCungUngSpinnerAdapter extends ArrayAdapter<CungUng> {
    Context context;
    ArrayList<CungUng> lists;
    TextView tvMa, tvTen;

    public DonViCungUngSpinnerAdapter(@NonNull Context context, ArrayList<CungUng> lists) {
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
            v = inflater.inflate(R.layout.spinner_dvcu, parent, false);
        }
        final CungUng item = lists.get(position);
        if(item != null){
            tvMa = v.findViewById(R.id.tvMaDVCU);
            tvMa.setText(item.getMaCungUng()+". ");
            tvTen = v.findViewById(R.id.tvtenDVCU);
            tvTen.setText(item.getTenDonVi());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (v == null) {
            v = inflater.inflate(R.layout.spinner_dvcu, parent, false);
        }
        final CungUng item = lists.get(position);
        if(item != null){
            tvMa = v.findViewById(R.id.tvMaDVCU);
            tvMa.setText(item.getMaCungUng()+". ");
            tvTen = v.findViewById(R.id.tvtenDVCU);
            tvTen.setText(item.getTenDonVi());
        }
        return v;
    }
}
