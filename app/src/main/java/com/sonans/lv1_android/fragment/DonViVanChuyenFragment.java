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
import com.sonans.lv1_android.Dao.DonViVanChuyenDao;
import com.sonans.lv1_android.Dao.KhachHangDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.adapter.DonViVanChuyenAdapter;
import com.sonans.lv1_android.adapter.KhachHangAdapter;
import com.sonans.lv1_android.model.DonViVanChuyen;
import com.sonans.lv1_android.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class DonViVanChuyenFragment extends Fragment {

    RecyclerView rcvVC;
    FloatingActionButton fab;
    DonViVanChuyenDao dao;
    DonViVanChuyenAdapter adapter;
    DonViVanChuyen item;
    List<DonViVanChuyen> list;
    public Context context;
    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_don_vi_van_chuyen, container, false);
        rcvVC = v.findViewById(R.id.rcvDVVC);
        rcvVC.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab = v.findViewById(R.id.fabDVVC);
        if (item == null) {
            item = new DonViVanChuyen();
        }
        DonViVanChuyenDao donViVanChuyenDao = new DonViVanChuyenDao(getActivity());
        list = new ArrayList<>();
        list = donViVanChuyenDao.getAll();
        adapter = new DonViVanChuyenAdapter(list, donViVanChuyenDao, context, this);
        rcvVC.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext(), 0);
            }
        });
        return v;
    }

    EditText tvName_VC, tvPhone_VC;
    ImageView imgSee;
    Button btnDel, btnUp;
    public void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_ud_dvvc);
        tvName_VC = dialog.findViewById(R.id.edTDVVC);
        tvPhone_VC = dialog.findViewById(R.id.edSDTDVVC);
        btnDel = dialog.findViewById(R.id.btnDelete_DVVC);
        btnUp = dialog.findViewById(R.id.btnUpdate_DVVCD);

        tvName_VC.setText("");
        tvPhone_VC.setText("");
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoa(String.valueOf(item.getMaVC()));
                dialog.dismiss();
            }
        });
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new DonViVanChuyenDao(getContext());
                item = new DonViVanChuyen();
                item.setTenDonVi(tvName_VC.getText().toString());
                item.setSdt(tvPhone_VC.getText().toString());
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
        if (tvName_VC.getText().length() == 0 || tvName_VC.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void capNhat() {
        dao = new DonViVanChuyenDao(getContext());
        list = dao.getAll();
        adapter = new DonViVanChuyenAdapter(list, dao, getContext(), this);
        rcvVC.setAdapter(adapter);
    }
}