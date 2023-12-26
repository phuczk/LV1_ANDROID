package com.sonans.lv1_android.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sonans.lv1_android.Dao.HangDao;
import com.sonans.lv1_android.Dao.LoaiHangDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.fragment.HangFragment;
import com.sonans.lv1_android.model.Hang;
import com.sonans.lv1_android.model.LoaiHang;

import java.util.ArrayList;
import java.util.List;

public class HangAdapter extends RecyclerView.Adapter<HangAdapter.ViewHolder>{
    List<Hang> list;
    Context context;
    HangDao dao;
    ArrayList<LoaiHang> loaiHangList;
    LoaiHangDao loaiHangDAO;
    LoaiHangAdapter loaiHangAdapter;

    Dialog dialog;
    LoaiHangSpinnerAdapter spinerAdapter;
    HangFragment fragment;
    Hang item;
    String img;

    public HangAdapter(List<Hang> list, Context context, HangDao dao, HangFragment fragment) {
        this.list = list;
        this.context = context;
        this.dao = dao;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public HangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_h, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HangAdapter.ViewHolder holder, int position) {
        item = list.get(position);
        LoaiHangDao loaiHangDAO1 = new LoaiHangDao(holder.itemView.getContext());
        LoaiHang loaiHang = loaiHangDAO1.getID(String.valueOf(item.getMaLoaiHang()));
        holder.tvTenHang.setText(list.get(position).getTenHang()+"");
        holder.tvGiaHang.setText(list.get(position).getGiaHang()+"");
        holder.tvSoLuong.setText(list.get(position).getSoLuong()+"");
        holder.tvLoaiHang.setText(loaiHang.getTenLoaiHang());
        img = item.getImageH();
        Glide.with(holder.itemView.getContext())
                .load(img)
                .placeholder(R.drawable.a)
                .error(R.drawable.b)
                .into(holder.ivH);
        holder.imgSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = list.get(position);
                openDialog(v.getContext(), 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenHang, tvGiaHang, tvLoaiHang, tvSoLuong;
        ImageView imgSee, ivH;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenHang = itemView.findViewById(R.id.tvHName);
            tvGiaHang = itemView.findViewById(R.id.tvHPrice);
            tvLoaiHang = itemView.findViewById(R.id.tvLHOH);
            tvSoLuong = itemView.findViewById(R.id.tvHNum);
            imgSee = itemView.findViewById(R.id.btnUDH);
            ivH = itemView.findViewById(R.id.ivH);
        }
    }

    EditText tvName, tvPrice, tvId, tvNum;
    Spinner spinner;
    ImageView ivH;
    Button btncan, btnsa;
    int maLoaiHang, position;
    public void openDialog(final Context context, final  int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_ud_h);
        tvId = dialog.findViewById(R.id.edMaH);
        tvName = dialog.findViewById(R.id.edNameH);
        tvPrice = dialog.findViewById(R.id.edPriceH);
        tvNum = dialog.findViewById(R.id.edNumH);
        spinner = dialog.findViewById(R.id.spLH);
        ivH = dialog.findViewById(R.id.imgH);
        btncan = dialog.findViewById(R.id.btnDelete_H);
        btnsa = dialog.findViewById(R.id.btnUpdate_H);
        loaiHangList = new ArrayList<LoaiHang>();
        loaiHangDAO = new LoaiHangDao(context);
        loaiHangList = (ArrayList<LoaiHang>) loaiHangDAO.getAll();
        spinerAdapter = new LoaiHangSpinnerAdapter(context, loaiHangList);
        spinner.setAdapter(spinerAdapter);
        tvId.setEnabled(false);
        ivH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đảm bảo fragment không phải là null
                if (fragment != null) {
                    fragment.pickThuVienFuntion();
                    // Cập nhật đường dẫn ảnh trong item sau khi chọn ảnh
                    if (fragment.selectedImageUri != null) {
                        img = fragment.selectedImageUri.toString();
                    }
                }
            }
        });

        if(item != null){
            tvId.setText(String.valueOf(item.getMaHang()));
            tvName.setText(item.getTenHang());
            tvPrice.setText(String.valueOf(item.getGiaHang()));
            tvNum.setText(String.valueOf(item.getSoLuong()));
            spinner.setSelection(item.getMaLoaiHang());
            img = item.getImageH();
            Glide.with(dialog.getContext())
                    .load(img)
                    .placeholder(R.drawable.a)
                    .error(R.drawable.b)
                    .into(ivH);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiHang = loaiHangList.get(position).getMaLoaiHang();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for (int i = 0; i < loaiHangList.size(); i++){
            if (item.getMaLoaiHang() == loaiHangList.get(i).getMaLoaiHang()){
                position = i;
            }
        }
        Log.i("//------------", "pos sach: "+position);
        spinner.setSelection(position);
        btncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaHang()));
                dialog.dismiss();
            }
        });
        btnsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Hang();
                item.setMaHang(Integer.parseInt(tvId.getText().toString()));
                item.setTenHang(tvName.getText().toString());
                item.setGiaHang(Integer.parseInt(tvPrice.getText().toString()));
                item.setSoLuong(Integer.parseInt(tvNum.getText().toString()));
                item.setMaLoaiHang(maLoaiHang);
                item.setImageH(img);
                if (validate()>0){
                    if(dao.update(item)>0){
                        Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "that bai", Toast.LENGTH_SHORT).show();
                    }
                    fragment.capNhat();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }
    public int validate(){
        int check = 1;
        if(tvName.getText().length() == 0 || tvPrice.getText().length()==0 || tvNum.getText().length() == 0){
            Toast.makeText(context, "chua nhap du thong tin", Toast.LENGTH_SHORT).show();
            check =-1;
        }
        return check;
    }
}
