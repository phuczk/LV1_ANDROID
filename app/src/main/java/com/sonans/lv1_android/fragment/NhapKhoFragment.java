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
import com.sonans.lv1_android.Dao.DonViCungUngDao;
import com.sonans.lv1_android.Dao.DonViVanChuyenDao;
import com.sonans.lv1_android.Dao.HangDao;
import com.sonans.lv1_android.Dao.HoaDonDao;
import com.sonans.lv1_android.Dao.KhachHangDao;
import com.sonans.lv1_android.Dao.NhapKhoDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.adapter.DonViCungUngSpinnerAdapter;
import com.sonans.lv1_android.adapter.DonViVanChuyenSpinnerAdapter;
import com.sonans.lv1_android.adapter.HangSpinnerAdapter;
import com.sonans.lv1_android.adapter.HoaDonAdapter;
import com.sonans.lv1_android.adapter.KhachHangSpinnerAdapter;
import com.sonans.lv1_android.adapter.NhapKhoAdater;
import com.sonans.lv1_android.model.CungUng;
import com.sonans.lv1_android.model.DonViVanChuyen;
import com.sonans.lv1_android.model.Hang;
import com.sonans.lv1_android.model.HoaDon;
import com.sonans.lv1_android.model.KhachHang;
import com.sonans.lv1_android.model.NhapKho;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NhapKhoFragment extends Fragment {
    FloatingActionButton fab;
    RecyclerView rcvHDNK;
    NhapKho item;

    NhapKhoDao dao;
    List<NhapKho> list;
    NhapKhoAdater adapter;
    public Context context;
    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nhap_kho, container, false);
        rcvHDNK = v.findViewById(R.id.rcvHDNK);
        fab = v.findViewById(R.id.fabHDNK);
        rcvHDNK.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (item == null) {
            item = new NhapKho();
        }
        NhapKhoDao nhapKhoDao = new NhapKhoDao(getActivity());
        capNhat();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        return v;
    }

    public void capNhat(){
        list = new ArrayList<>();
        dao = new NhapKhoDao(getContext());
        list = dao.getAll();
        adapter = new NhapKhoAdater(getContext(),this, list, dao);
        rcvHDNK.setAdapter(adapter);
    }

    EditText tvMaHDNK, tvSoluong, tvprice;
    TextView tvDate;
    Spinner spCU, spVC, spH;
    Button btncan, btnsa;
    ArrayList<CungUng> culist;
    ArrayList<DonViVanChuyen> vcList;
    ArrayList<Hang> hangList;
    DonViCungUngDao donViCungUngDao;
    DonViVanChuyenDao donViVanChuyenDao;
    HangDao hangDao;
    DonViCungUngSpinnerAdapter SPCU;
    DonViVanChuyenSpinnerAdapter SPVC;
    HangSpinnerAdapter SPH;
    int maCU, maVC, position, positionz, positionx, maH, tienThue;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public void openDialog(final Context context, final  int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_ud_nk);
        tvMaHDNK = dialog.findViewById(R.id.edMaHDNK);
        tvSoluong = dialog.findViewById(R.id.edNumHDNK);
        tvprice = dialog.findViewById(R.id.edPriceHDNK);
        tvDate = dialog.findViewById(R.id.tvDateNK);
        spH = dialog.findViewById(R.id.spH);
        spCU = dialog.findViewById(R.id.spDVCU);
        spVC = dialog.findViewById(R.id.spDVVC);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        culist = new ArrayList<CungUng>();
        donViCungUngDao = new DonViCungUngDao(context);
        culist = (ArrayList<CungUng>) donViCungUngDao.getAll();
        SPCU = new DonViCungUngSpinnerAdapter(context, culist);
        spCU.setAdapter(SPCU);

        spCU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maCU = culist.get(position).getMaCungUng();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        vcList = new ArrayList<DonViVanChuyen>();
        donViVanChuyenDao = new DonViVanChuyenDao(context);
        vcList = (ArrayList<DonViVanChuyen>) donViVanChuyenDao.getAll();
        SPVC = new DonViVanChuyenSpinnerAdapter(context, vcList);
        spVC.setAdapter(SPVC);

        spVC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maVC = vcList.get(position).getMaVC();
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
            if (item.getMaHangNK() == hangList.get(i).getMaHang()){
                position = i;
            }
        }
        int old = hangList.get(position).getSoLuong();
        Log.d("////////////", String.valueOf(old));
        Log.i("//------------", "pos sach: "+positionz);
        spH.setSelection(positionz);
        for (int i = 0; i < culist.size(); i++){
            if (item.getMaCungUng() == culist.get(i).getMaCungUng()){
                positionz = i;
            }
        }
        for (int i = 0; i < vcList.size(); i++){
            if (item.getMaVC() == vcList.get(i).getMaVC()){
                positionx = i;
            }
        }
        Log.i("//------------", "pos sach: "+position);
        spCU.setSelection(position);
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
                int soLuongConLai = selectedHang.getSoLuong() + soLuongMua;
                selectedHang.setSoLuong(soLuongConLai);
                HangDao hangDao = new HangDao(context);
                hangDao.update(selectedHang);
                item = new NhapKho();
                item.setMaHangNK(maH);
                item.setSoLuong(Integer.parseInt(tvSoluong.getText().toString()));
                item.setNgayNhap(new Date());
                item.setMaVC(maVC);
                item.setMaCungUng(maCU);
                item.setGiaHang(Integer.parseInt(tvprice.getText().toString()));
                int tien = giaHang * soLuongHang;

                Toast.makeText(context, "tien thue "+tien, Toast.LENGTH_SHORT).show();

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
                }
                capNhat();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}