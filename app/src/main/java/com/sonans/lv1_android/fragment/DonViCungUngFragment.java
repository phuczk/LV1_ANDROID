package com.sonans.lv1_android.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sonans.lv1_android.Dao.DonViCungUngDao;
import com.sonans.lv1_android.Dao.DonViVanChuyenDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.adapter.DonViCungUngAdapter;
import com.sonans.lv1_android.adapter.DonViVanChuyenAdapter;
import com.sonans.lv1_android.model.CungUng;
import com.sonans.lv1_android.model.DonViVanChuyen;

import java.util.ArrayList;
import java.util.List;

public class DonViCungUngFragment extends Fragment {

    RecyclerView rcvCU;
    FloatingActionButton fab;
    DonViCungUngDao dao;
    DonViCungUngAdapter adapter;
    CungUng item;
    List<CungUng> list;
    public Context context;
    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_don_vi_cung_ung, container, false);
        rcvCU = v.findViewById(R.id.rcvDVCU);
        rcvCU.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab = v.findViewById(R.id.fabDVCU);
        if (item == null) {
            item = new CungUng();
        }
        DonViCungUngDao donViCungUngDao = new DonViCungUngDao(getActivity());
        list = new ArrayList<>();
        list = donViCungUngDao.getAll();
        adapter = new DonViCungUngAdapter(list, donViCungUngDao, context, this);
        rcvCU.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext(), 0);
            }
        });
        return v;
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

        tvName_CU.setText("");
        tvPhone_CU.setText("");
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoa(String.valueOf(item.getMaCungUng()));
                dialog.dismiss();
            }
        });
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new DonViCungUngDao(getContext());
                item = new CungUng();
                item.setTenDonVi(tvName_CU.getText().toString());
                item.setSdtCungUng(tvPhone_CU.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "that bai", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "update thanh cong", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(context, "update thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhat();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoa(final String ma) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(ma);
                capNhat();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public int validate() {
        int check = 1;
        if (tvName_CU.getText().length() == 0 || tvName_CU.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void capNhat() {
        dao = new DonViCungUngDao(getContext());
        list = dao.getAll();
        adapter = new DonViCungUngAdapter(list, dao, getContext(), this);
        rcvCU.setAdapter(adapter);
    }
}