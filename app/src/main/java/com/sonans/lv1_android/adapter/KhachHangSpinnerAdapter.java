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
import com.sonans.lv1_android.model.KhachHang;
import com.sonans.lv1_android.model.LoaiHang;

import java.util.ArrayList;

public class KhachHangSpinnerAdapter extends ArrayAdapter<KhachHang> {

    Context context;
    ArrayList<KhachHang> lists;
    TextView tvMa, tvTen;

    public KhachHangSpinnerAdapter(@NonNull Context context, ArrayList<KhachHang> lists) {
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
            v = inflater.inflate(R.layout.spinner_khach_hang, parent, false);
        }
        final KhachHang item = lists.get(position);
        if(item != null){
            tvMa = v.findViewById(R.id.tvMaKhachHangSp);
            tvMa.setText(item.getMaKhachHang()+". ");
            tvTen = v.findViewById(R.id.tvtenKhachHangSp);
            tvTen.setText(item.getTenKH());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (v == null) {
            v = inflater.inflate(R.layout.spinner_khach_hang, parent, false);
        }
        final KhachHang item = lists.get(position);
        if(item != null){
            tvMa = v.findViewById(R.id.tvMaKhachHangSp);
            tvMa.setText(item.getMaKhachHang()+". ");
            tvTen = v.findViewById(R.id.tvtenKhachHangSp);
            tvTen.setText(item.getTenKH());
        }
        return v;
    }
}
