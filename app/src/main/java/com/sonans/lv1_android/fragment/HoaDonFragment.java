package com.sonans.lv1_android.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sonans.lv1_android.Dao.HangDao;
import com.sonans.lv1_android.Dao.HoaDonDao;
import com.sonans.lv1_android.Dao.KhachHangDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.adapter.HangSpinnerAdapter;
import com.sonans.lv1_android.adapter.HoaDonAdapter;
import com.sonans.lv1_android.adapter.KhachHangSpinnerAdapter;
import com.sonans.lv1_android.model.Hang;
import com.sonans.lv1_android.model.HoaDon;
import com.sonans.lv1_android.model.KhachHang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HoaDonFragment extends Fragment {

    FloatingActionButton fab;
    RecyclerView rcvHD;
    HoaDon item;

    HoaDonDao dao;
    List<HoaDon> list;
    HoaDonAdapter adapter;
    public Context context;
    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);
        rcvHD = view.findViewById(R.id.rcvHD);
        fab = view.findViewById(R.id.fabHD);
        rcvHD.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (item == null) {
            item = new HoaDon();
        }
        HoaDonDao hoaDonDao = new HoaDonDao(getActivity());
        capNhat();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        return view;
    }

    public void capNhat(){
        list = new ArrayList<>();
        dao = new HoaDonDao(getContext());
        list = dao.getAll();
        adapter = new HoaDonAdapter(getContext(),this, list, dao);
        rcvHD.setAdapter(adapter);
    }

    EditText tvMaHD, tvSoluong;
    TextView tvDate, tvPrice;
    Spinner spKH, spH;
    Button btncan, btnsa;
    ArrayList<KhachHang> khList;
    ArrayList<Hang> hangList;
    KhachHangDao khachHangDao;
    HangDao hangDao;
    KhachHangSpinnerAdapter SPKH;
    HangSpinnerAdapter SPH;
    int maKh, position, positionz, maH, tienThue;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public void openDialog(final Context context, final  int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_ud_hd);
        tvMaHD = dialog.findViewById(R.id.edMaHD);
        tvSoluong = dialog.findViewById(R.id.edNumHD);
        tvDate = dialog.findViewById(R.id.tvDateHD);
        tvPrice = dialog.findViewById(R.id.tvPriceHD);
        spH = dialog.findViewById(R.id.spTH);
        spKH = dialog.findViewById(R.id.spKH);
        btncan = dialog.findViewById(R.id.btnDelete_HD);
        btnsa = dialog.findViewById(R.id.btnUpdate_HDD);

//        khList = new ArrayList<KhachHang>();
//        khachHangDao = new KhachHangDao(context);
//        khList = (ArrayList<KhachHang>) khachHangDao.getAll();
//        SPKH = new KhachHangSpinnerAdapter(context, khList);
//        spKH.setAdapter(SPKH);

        hangList = new ArrayList<Hang>();
        hangDao = new HangDao(context);
        hangList = (ArrayList<Hang>) hangDao.getAll();
        SPH = new HangSpinnerAdapter(context, hangList);
        spH.setAdapter(SPH);

        spH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maH = hangList.get(position).getMaHang();
                tienThue = hangList.get(position).getGiaHang();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        khList = new ArrayList<KhachHang>();
        khachHangDao = new KhachHangDao(context);
        khList = (ArrayList<KhachHang>) khachHangDao.getAll();
        SPKH = new KhachHangSpinnerAdapter(context, khList);
        spKH.setAdapter(SPKH);

        spKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maKh = khList.get(position).getMaKhachHang();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (item != null) {
            tvDate.setText(sdf.format(new Date()));

        } else {
            tvDate.setText("");
        }
        for (int i = 0; i < hangList.size(); i++){
            if (item.getMaHang() == hangList.get(i).getMaHang()){
                position = i;
            }
        }
        int old = hangList.get(position).getSoLuong();
        Log.d("////////////", String.valueOf(old));
        Log.i("//------------", "pos sach: "+positionz);
        spH.setSelection(positionz);
        for (int i = 0; i < khList.size(); i++){
            if (item.getMaKhachHang() == khList.get(i).getMaKhachHang()){
                positionz = i;
            }
        }
        Log.i("//------------", "pos sach: "+position);
        spKH.setSelection(position);
        btncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int giaHang = hangList.get(position).getGiaHang();
                int soLuongHang = Integer.parseInt(tvSoluong.getText().toString());
                Hang selectedHang = (Hang) spH.getSelectedItem();
                int maHang = selectedHang.getMaHang();
                int soLuongMua = Integer.parseInt(tvSoluong.getText().toString());
                int soLuongConLai = selectedHang.getSoLuong() - soLuongMua;
                selectedHang.setSoLuong(soLuongConLai);
                HangDao hangDao = new HangDao(context);
                hangDao.update(selectedHang);
                item = new HoaDon();
                item.setMaHoaDon(tvMaHD.getText().toString());
                item.setMaHang(maH);
                item.setSoLuongHangMua(Integer.parseInt(tvSoluong.getText().toString()));
                item.setNgayXuatHoaDon(new Date());
                item.setMaKhachHang(maKh);
                int tien = giaHang * soLuongHang;
                item.setTongTien(tien);

                if(validate() > 0){
                    if(type == 0){
                        if(dao.insert(item) > 0){
                            Toast.makeText(context, "them thanh cong", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "them that bai", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        if(dao.update(item) >0){
                            Toast.makeText(context, "update thanh cong", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "update that bai", Toast.LENGTH_SHORT).show();
                        }
                    }capNhat();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (tvMaHD.getText().length() == 0 || tvPrice.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}