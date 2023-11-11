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

import com.sonans.lv1_android.Dao.DonViCungUngDao;
import com.sonans.lv1_android.Dao.DonViVanChuyenDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.fragment.DonViCungUngFragment;
import com.sonans.lv1_android.fragment.DonViVanChuyenFragment;
import com.sonans.lv1_android.model.CungUng;
import com.sonans.lv1_android.model.DonViVanChuyen;

import java.util.List;

public class DonViCungUngAdapter extends RecyclerView.Adapter<DonViCungUngAdapter.ViewHolder>{
    List<CungUng> list;
    DonViCungUngDao donViCungUngDao;
    Context context;
    DonViCungUngFragment fragment;
    CungUng item;

    Dialog dialog;
    public DonViCungUngAdapter(List<CungUng> list, DonViCungUngDao donViCungUngDao, Context context, DonViCungUngFragment fragment) {
        this.list = list;
        this.donViCungUngDao = donViCungUngDao;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public DonViCungUngAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_dvcu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonViCungUngAdapter.ViewHolder holder, int position) {
        item = list.get(position);
        holder.tvCUName.setText(item.getTenDonVi());
        holder.tvCUPhone.setText(item.getSdtCungUng());
        holder.ivUD.setOnClickListener(new View.OnClickListener() {
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

    EditText tvName_CU, tvPhone_CU;
    ImageView imgSee;
    Button btnDel, btnUp;
    public void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_dvcu);
        tvName_CU = dialog.findViewById(R.id.edTDVCU);
        tvPhone_CU = dialog.findViewById(R.id.edSDTDVCU);
        btnDel = dialog.findViewById(R.id.btnDelete_DVCU);
        btnUp = dialog.findViewById(R.id.btnUpdate_DVCUD);

        if(item != null){
            tvName_CU.setText(item.getTenDonVi());
            tvPhone_CU.setText(item.getSdtCungUng());
        }

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaCungUng()));
                dialog.dismiss();
            }
        });
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setTenDonVi(tvName_CU.getText().toString());
                item.setSdtCungUng(tvPhone_CU.getText().toString());
                if (validate() > 0) {
                        if (donViCungUngDao.update(item) > 0) {
                            Toast.makeText(context, "update thanh cong", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(context, "update thất bại", Toast.LENGTH_SHORT).show();
                        }

                    fragment.capNhat();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCUName, tvCUPhone;
        ImageView ivLH, ivUD;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCUName = itemView.findViewById(R.id.tvTenDVCU);
            tvCUPhone = itemView.findViewById(R.id.tvSDTDVCU);
            ivUD = itemView.findViewById(R.id.btnUDDVCU);
        }
    }
    public int validate() {
        int check = 1;
        if (tvName_CU.getText().length() == 0 || tvPhone_CU.getText().length() == 0) {
            Toast.makeText(context, "nhap du du lieu", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}
