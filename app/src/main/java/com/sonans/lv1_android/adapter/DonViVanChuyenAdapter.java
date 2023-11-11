package com.sonans.lv1_android.adapter;

import android.app.Dialog;
import android.content.Context;
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
import com.sonans.lv1_android.Dao.DonViVanChuyenDao;
import com.sonans.lv1_android.Dao.KhachHangDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.fragment.DonViVanChuyenFragment;
import com.sonans.lv1_android.fragment.KhachHangFragment;
import com.sonans.lv1_android.model.DonViVanChuyen;
import com.sonans.lv1_android.model.KhachHang;

import java.util.List;

public class DonViVanChuyenAdapter extends RecyclerView.Adapter<DonViVanChuyenAdapter.ViewHolder>{
    List<DonViVanChuyen> list;
    DonViVanChuyenDao donViVanChuyenDao;
    Context context;
    DonViVanChuyenFragment fragment;
    DonViVanChuyen item;

    Dialog dialog;
    public DonViVanChuyenAdapter(List<DonViVanChuyen> list, DonViVanChuyenDao donViVanChuyenDao, Context context, DonViVanChuyenFragment fragment) {
        this.list = list;
        this.donViVanChuyenDao = donViVanChuyenDao;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public DonViVanChuyenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_dvvc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonViVanChuyenAdapter.ViewHolder holder, int position) {
        item = list.get(position);
        holder.tvVCName.setText(item.getTenDonVi());
        holder.tvVCPhone.setText(item.getSdt());
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

    EditText tvName_VC, tvPhone_VC;
    ImageView imgSee;
    Button btnDel, btnUp;
    int positionn;
    public void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_ud_dvvc);
        tvName_VC = dialog.findViewById(R.id.edTDVVC);
        tvPhone_VC = dialog.findViewById(R.id.edSDTDVVC);
        btnDel = dialog.findViewById(R.id.btnDelete_DVVC);
        btnUp = dialog.findViewById(R.id.btnUpdate_DVVCD);

        if(item != null){
            tvName_VC.setText(item.getTenDonVi());
            tvPhone_VC.setText(item.getSdt());
        }

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaVC()));
                dialog.dismiss();
            }
        });
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item.setTenDonVi(tvName_VC.getText().toString());
                item.setSdt(tvPhone_VC.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (donViVanChuyenDao.insert(item) > 0) {
                            Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "that bai", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (donViVanChuyenDao.update(item) > 0) {
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
        TextView tvVCName, tvVCPhone;
        ImageView ivLH, ivUD;

        public ViewHolder(View itemView) {
            super(itemView);
            tvVCName = itemView.findViewById(R.id.tvTenDVVC);
            tvVCPhone = itemView.findViewById(R.id.tvSDTDVVC);
            ivUD = itemView.findViewById(R.id.btnUDDVVC);
        }
    }
    public int validate() {
        int check = 1;
        if (tvName_VC.getText().length() == 0 || tvPhone_VC.getText().length() == 0) {
            Toast.makeText(context, "nhap du du lieu", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}
