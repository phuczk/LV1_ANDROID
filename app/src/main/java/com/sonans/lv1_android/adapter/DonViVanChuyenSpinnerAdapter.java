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
import com.sonans.lv1_android.model.DonViVanChuyen;

import java.util.ArrayList;

public class DonViVanChuyenSpinnerAdapter extends ArrayAdapter<DonViVanChuyen> {
    Context context;
    ArrayList<DonViVanChuyen> lists;
    TextView tvMa, tvTen;

    public DonViVanChuyenSpinnerAdapter(@NonNull Context context, ArrayList<DonViVanChuyen> lists) {
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
            v = inflater.inflate(R.layout.spinner_dvvc, parent, false);
        }
        final DonViVanChuyen item = lists.get(position);
        if(item != null){
            tvMa = v.findViewById(R.id.tvMaDVVC);
            tvMa.setText(item.getMaVC()+". ");
            tvTen = v.findViewById(R.id.tvtenDVVC);
            tvTen.setText(item.getTenDonVi());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (v == null) {
            v = inflater.inflate(R.layout.spinner_dvvc, parent, false);
        }
        final DonViVanChuyen item = lists.get(position);
        if(item != null){
            tvMa = v.findViewById(R.id.tvMaDVVC);
            tvMa.setText(item.getMaVC()+". ");
            tvTen = v.findViewById(R.id.tvtenDVVC);
            tvTen.setText(item.getTenDonVi());
        }
        return v;
    }
}
