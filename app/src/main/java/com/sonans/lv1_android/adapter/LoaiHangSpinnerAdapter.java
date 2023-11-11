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
import com.sonans.lv1_android.model.LoaiHang;

import java.util.ArrayList;

public class LoaiHangSpinnerAdapter extends ArrayAdapter<LoaiHang> {
    Context context;
    ArrayList<LoaiHang> lists;
    TextView tvMa, tvTen;

    public LoaiHangSpinnerAdapter(@NonNull Context context, ArrayList<LoaiHang> lists) {
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
            v = inflater.inflate(R.layout.spinner_loai_hang, parent, false);
        }
        final LoaiHang item = lists.get(position);
        if(item != null){
            tvMa = v.findViewById(R.id.tvMaLoaiHangSp);
            tvMa.setText(item.getMaLoaiHang()+". ");
            tvTen = v.findViewById(R.id.tvtenLoaiHangSp);
            tvTen.setText(item.getTenLoaiHang());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (v == null) {
            v = inflater.inflate(R.layout.spinner_loai_hang, parent, false);
        }
        final LoaiHang item = lists.get(position);
        if(item != null){
            tvMa = v.findViewById(R.id.tvMaLoaiHangSp);
            tvMa.setText(item.getMaLoaiHang()+". ");
            tvTen = v.findViewById(R.id.tvtenLoaiHangSp);
            tvTen.setText(item.getTenLoaiHang());
        }
        return v;
    }
}
