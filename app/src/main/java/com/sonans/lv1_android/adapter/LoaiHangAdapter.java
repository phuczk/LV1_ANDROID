package com.sonans.lv1_android.adapter;

import android.app.AlertDialog;
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

import com.bumptech.glide.Glide;
import com.sonans.lv1_android.Dao.LoaiHangDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.fragment.LoaiHangFragment;
import com.sonans.lv1_android.model.LoaiHang;

import java.util.List;

public class LoaiHangAdapter extends RecyclerView.Adapter<LoaiHangAdapter.ViewHolder> {
    List<LoaiHang> list;
    LoaiHangDao loaiHangDao;
    Context context;
    LoaiHangFragment fragment;
    LoaiHang item;
    Uri selectedImg;

    public LoaiHangAdapter(List<LoaiHang> list, LoaiHangDao loaiHangDao, Context context, LoaiHangFragment fragment) {
        this.list = list;
        this.loaiHangDao = loaiHangDao;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_hang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        item = list.get(position);
        String img = item.getImageLH();
        Glide.with(holder.itemView.getContext())
                .load(img)
                .placeholder(R.drawable.a)
                .error(R.drawable.b)
                .into(holder.ivLH);

        holder.tvLHName.setText(item.getTenLoaiHang());

        holder.ivUD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_ud_lh, null);
                builder.setView(dialogView);
                final EditText txtID = dialogView.findViewById(R.id.edMaLH);
                final EditText txtName = dialogView.findViewById(R.id.edNameLH);
                final ImageView imgUD = dialogView.findViewById(R.id.imgLH);
                final Button btnUp = dialogView.findViewById(R.id.btnUpdate_LH);
                final Button btnDel = dialogView.findViewById(R.id.btnDelete_LH);
                txtID.setText(String.valueOf(item.getMaLoaiHang()));
                txtName.setText(item.getTenLoaiHang());
                Glide.with(dialogView)
                        .load(img)
                        .placeholder(R.drawable.a)
                        .error(R.drawable.b)
                        .into(imgUD);

                imgUD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragment.pickThuVienFuntion(); // Truyền item vào để có thể cập nhật hình ảnh
                    }
                });
                btnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loaiHangDao.delete(String.valueOf(item.getMaLoaiHang()));
                        Toast.makeText(v.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                        fragment.capNhat();
                    }
                });

                btnUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newName = txtName.getText().toString().trim();
                        if (!newName.isEmpty()) {
                            String newImagePath = item.getImageLH(); // Lấy đường dẫn hình ảnh mới
                            item.setTenLoaiHang(newName);
                            item.setImageLH(newImagePath);
                            loaiHangDao.update(item);
                            Toast.makeText(v.getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            fragment.capNhat();
                        } else {
                            Toast.makeText(v.getContext(), "Vui lòng nhập tên loại hàng", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ChucNangInterFace {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLHName;
        ImageView ivLH, ivUD;

        public ViewHolder(View itemView) {
            super(itemView);
            tvLHName = itemView.findViewById(R.id.tvLHName);
            ivLH = itemView.findViewById(R.id.ivLH);
            ivUD = itemView.findViewById(R.id.btnUDLH);
        }
    }
}