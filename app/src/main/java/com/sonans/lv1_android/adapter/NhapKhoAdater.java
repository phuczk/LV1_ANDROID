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

import com.sonans.lv1_android.Dao.DonViVanChuyenDao;
import com.sonans.lv1_android.Dao.HangDao;
import com.sonans.lv1_android.Dao.HoaDonDao;
import com.sonans.lv1_android.Dao.KhachHangDao;
import com.sonans.lv1_android.Dao.NhapKhoDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.fragment.HoaDonFragment;
import com.sonans.lv1_android.fragment.NhapKhoFragment;
import com.sonans.lv1_android.model.DonViVanChuyen;
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
        item = lists.get(position);
        HangDao hangDao = new HangDao(holder.itemView.getContext());
        Hang hang = hangDao.getID(String.valueOf(item.getMaHangNK()));
        DonViVanChuyenDao donViVanChuyenDao = new DonViVanChuyenDao(holder.itemView.getContext());
        DonViVanChuyen donViVanChuyen = donViVanChuyenDao.getID(String.valueOf(item.getMaVC()));
        holder.tvHD_name.setText(hang.getTenHang());
        holder.tvHD_soLuong.setText(String.valueOf(lists.get(position).getSoLuong()));
        holder.tvHD_vanChuyen.setText(donViVanChuyen.getTenDonVi());
        holder.tvHD_ngay.setText(sdf.format(lists.get(position).getNgayNhap()));
        holder.tvHD_tongTien.setText(String.valueOf(item.getSoLuong() * item.getGiaHang()));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHD_name, tvHD_soLuong, tvHD_ngay, tvHD_tongTien, tvHD_vanChuyen;
        ImageView imgSee;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHD_name = itemView.findViewById(R.id.tenH_NK);
            tvHD_soLuong = itemView.findViewById(R.id.soLuong_NK);
            tvHD_ngay = itemView.findViewById(R.id.tzan_NK);
            tvHD_tongTien = itemView.findViewById(R.id.tongTien_NK);
            tvHD_vanChuyen = itemView.findViewById(R.id.vanChuen_NK);
            imgSee = itemView.findViewById(R.id.btnUpdate_HD);
        }
    }
}
