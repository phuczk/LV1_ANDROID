package com.sonans.lv1_android.adapter;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sonans.lv1_android.Dao.KhachHangDao;
import com.sonans.lv1_android.Dao.LoaiHangDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.fragment.KhachHangFragment;
import com.sonans.lv1_android.fragment.LoaiHangFragment;
import com.sonans.lv1_android.model.KhachHang;
import com.sonans.lv1_android.model.LoaiHang;

import java.util.List;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder>{
    List<KhachHang> list;
    KhachHangDao khachHangDao;
    Context context;
    KhachHangFragment fragment;
    KhachHang item;

    Dialog dialog;
    public KhachHangAdapter(List<KhachHang> list, KhachHangDao khachHangDao, Context context, KhachHangFragment fragment) {
        this.list = list;
        this.khachHangDao = khachHangDao;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public KhachHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_kh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangAdapter.ViewHolder holder, int position) {
        item = list.get(position);
        holder.tvKHName.setText(item.getTenKH());
        holder.tvKHPhone.setText(item.getSdt());
        holder.ivUD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = list.get(position);
                openDialog(v.getContext(), 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    EditText tvName_KH, tvPhone_KH;
    ImageView imgSee;
    Button btnDel, btnUp;
    public void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_ud_kh);
        tvName_KH = dialog.findViewById(R.id.edTKH);
        tvPhone_KH = dialog.findViewById(R.id.edSDTKH);
        btnDel = dialog.findViewById(R.id.btnDelete_KH);
        btnUp = dialog.findViewById(R.id.btnUpdate_KHD);

        tvName_KH.setText("");
        tvPhone_KH.setText("");

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaKhachHang()));
                dialog.dismiss();
            }
        });
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setTenKH(tvName_KH.getText().toString());
                item.setSdt(tvPhone_KH.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (khachHangDao.insert(item) > 0) {
                            Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "that bai", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (khachHangDao.update(item) > 0) {
                            Toast.makeText(context, "update thanh cong", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(context, "update thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    fragment.capNhat();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKHName, tvKHPhone;
        ImageView ivLH, ivUD;

        public ViewHolder(View itemView) {
            super(itemView);
            tvKHName = itemView.findViewById(R.id.tvTen_KH);
            tvKHPhone = itemView.findViewById(R.id.tvSDT_KH);
            ivUD = itemView.findViewById(R.id.btnUDKH);
        }
    }
    public int validate() {
        int check = 1;
        if (tvName_KH.getText().length() == 0 || tvPhone_KH.getText().length() == 0) {
            Toast.makeText(context, "nhap du du lieu", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

}
