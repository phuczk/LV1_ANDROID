package com.sonans.lv1_android.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sonans.lv1_android.Dao.KhachHangDao;
import com.sonans.lv1_android.Dao.LoaiHangDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.adapter.KhachHangAdapter;
import com.sonans.lv1_android.adapter.LoaiHangAdapter;
import com.sonans.lv1_android.model.KhachHang;
import com.sonans.lv1_android.model.LoaiHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangFragment extends Fragment {

    RecyclerView rcvKH;
    FloatingActionButton fab;
    KhachHangDao dao;
    KhachHangAdapter adapter;
    KhachHang item;
    List<KhachHang> list;
    public Context context;
    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        rcvKH = v.findViewById(R.id.rcvLH);
        rcvKH.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab = v.findViewById(R.id.fab);
        if (item == null) {
            item = new KhachHang();
        }
        KhachHangDao khachHangDao = new KhachHangDao(getActivity());
        list = new ArrayList<>();
        list = khachHangDao.getAll();
        adapter = new KhachHangAdapter(list, khachHangDao, context, this);
        rcvKH.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext(), 0);
            }
        });
        return v;
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
                xoa(String.valueOf(item.getMaKhachHang()));
                dialog.dismiss();
            }
        });
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new KhachHangDao(getContext());
                item = new KhachHang();
                item.setTenKH(tvName_KH.getText().toString());
                item.setSdt(tvPhone_KH.getText().toString());
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
        if (tvName_KH.getText().length() == 0 || tvPhone_KH.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void capNhat() {
        dao = new KhachHangDao(getContext());
        list = dao.getAll();
        adapter = new KhachHangAdapter(list, dao, getContext(), this);
        rcvKH.setAdapter(adapter);
    }
}