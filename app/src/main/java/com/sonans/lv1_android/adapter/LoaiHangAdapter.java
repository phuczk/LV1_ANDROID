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

    String img;

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

    // Trong ViewHolder của LoaiHangAdapter
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

    // Trong onBindViewHolder của LoaiHangAdapter
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        item = list.get(position);
        img = item.getImageLH();
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
                AlertDialog dialog = builder.create();
                final EditText txtID = dialogView.findViewById(R.id.edMaLH);
                final EditText txtName = dialogView.findViewById(R.id.edNameLH);
                final ImageView imgUD = dialogView.findViewById(R.id.imgLH);
                final Button btnUp = dialogView.findViewById(R.id.btnUpdate_LH);
                final Button btnDel = dialogView.findViewById(R.id.btnDelete_LH);
                txtID.setText(String.valueOf(item.getMaLoaiHang()));
                txtName.setText(item.getTenLoaiHang());
                img = item.getImageLH(); // Cập nhật đường dẫn ảnh mới
                Glide.with(dialogView)
                        .load(img)
                        .placeholder(R.drawable.a)
                        .error(R.drawable.b)
                        .into(imgUD);
                imgUD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Đảm bảo fragment không phải là null
                        if (fragment != null) {
                            fragment.pickThuVienFuntion();
                            // Cập nhật đường dẫn ảnh trong item sau khi chọn ảnh
                            if (fragment.selectedImageUri != null) {
                                img = item.getImageLH();
                                Glide.with(dialogView)
                                        .load(img)
                                        .placeholder(R.drawable.a)
                                        .error(R.drawable.b)
                                        .into(imgUD);
                            }
                        }
                    }
                });
                btnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loaiHangDao.delete(String.valueOf(item.getMaLoaiHang()));
                        builder.create().dismiss();
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        fragment.capNhat();
                    }
                });

                btnUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newName = txtName.getText().toString().trim();
                        if (!newName.isEmpty()) {
                            item.setTenLoaiHang(newName);
                            if (item != null && fragment.selectedImageUri != null) {
                                item.setImageLH(fragment.selectedImageUri.toString());
                                // Các bước cập nhật khác (nếu cần)
                            } else {
                                // Xử lý nếu item hoặc selectedImageUri là null
                            }

                            loaiHangDao.update(item);
                            dialog.dismiss();
                            // Cập nhật danh sách và thông báo cho Adapter
                            list.set(position, item);
                            notifyDataSetChanged();

                            Toast.makeText(v.getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            fragment.capNhat();
                        } else {
                            Toast.makeText(v.getContext(), "Vui lòng nhập tên loại hàng", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


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

}