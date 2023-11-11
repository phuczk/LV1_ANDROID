package com.sonans.lv1_android.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sonans.lv1_android.Dao.HangDao;
import com.sonans.lv1_android.Dao.HoaDonDao;
import com.sonans.lv1_android.Dao.KhachHangDao;
import com.sonans.lv1_android.Dao.NhapKhoDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.fragment.HoaDonFragment;
import com.sonans.lv1_android.fragment.NhapKhoFragment;
import com.sonans.lv1_android.model.Hang;
import com.sonans.lv1_android.model.HoaDon;
import com.sonans.lv1_android.model.KhachHang;
import com.sonans.lv1_android.model.NhapKho;

import java.text.SimpleDateFormat;
import java.util.List;

public class NhapKhoAdater extends RecyclerView.Adapter<NhapKhoAdater.ViewHolder>{
    Context context;
    NhapKhoFragment fragment;
    List<NhapKho> lists;
    NhapKhoDao dao;
    NhapKho item;
    Dialog dialog;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public NhapKhoAdater(@NonNull Context context, NhapKhoFragment fragment, List<NhapKho> lists, NhapKhoDao dao) {
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
        this.dao = dao;
    }
    @NonNull
    @Override
    public NhapKhoAdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_hoa_don, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhapKhoAdater.ViewHolder holder, int position) {
//        item = lists.get(position);
//        HangDao hangDao = new HangDao(holder.itemView.getContext());
//        Hang hang = hangDao.getID(String.valueOf(item.getMaHang()));
//        KhachHangDao khachHangDao = new KhachHangDao(holder.itemView.getContext());
//        KhachHang khachHang = khachHangDao.getID(String.valueOf(item.getMaKhachHang()));
//        holder.tvHD_name.setText(hang.getTenHang());
//        holder.tvHD_soLuong.setText(String.valueOf(item.getSoLuongHangMua()));
//        holder.tvHD_khachHang.setText(khachHang.getTenKH());
//        holder.tvHD_tongTien.setText(String.valueOf(item.getSoLuongHangMua() * hang.getGiaHang()));
//        holder.tvHD_ngay.setText(sdf.format(item.getNgayXuatHoaDon()));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHD_name, tvHD_soLuong, tvHD_ngay, tvHD_tongTien, tvHD_khachHang;
        ImageView imgSee;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHD_name = itemView.findViewById(R.id.tvHD_TH);
            tvHD_soLuong = itemView.findViewById(R.id.tvHD_SL);
            tvHD_ngay = itemView.findViewById(R.id.tvHD_N);
            tvHD_tongTien = itemView.findViewById(R.id.tvHD_TT);
            tvHD_khachHang = itemView.findViewById(R.id.tvHD_KH);
            imgSee = itemView.findViewById(R.id.btnUpdate_HD);
        }
    }
}
