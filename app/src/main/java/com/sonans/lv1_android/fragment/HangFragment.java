package com.sonans.lv1_android.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sonans.lv1_android.Dao.HangDao;
import com.sonans.lv1_android.Dao.LoaiHangDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.adapter.HangAdapter;
import com.sonans.lv1_android.adapter.LoaiHangSpinnerAdapter;
import com.sonans.lv1_android.model.Hang;
import com.sonans.lv1_android.model.LoaiHang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class HangFragment extends Fragment {

    RecyclerView rcvH;
    Dialog dialog;
    HangDao dao;
    Hang item;
    HangAdapter adapter;
    FloatingActionButton fab;
    List<Hang> list;
    public Context context;
    ArrayList<LoaiHang> loaiHangList;
    LoaiHangDao loaiHangDAO;
    EditText tvName, tvPrice, tvNum;
    Button btncan, btnsa;
    Spinner spinner;
    LoaiHangSpinnerAdapter spinerAdapter;
    int maLoaiHang, position;
    public Uri selectedImageUri;
    ImageView ivH;
    private static final String SHARED_PREFS_KEY_H = "MyAppPreferences_H";
    private static final String IMAGE_URI_KEY_H = "image_uri_H";
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hang, container, false);
        rcvH = v.findViewById(R.id.rcvH);
        rcvH.setLayoutManager(new LinearLayoutManager(getActivity()));
        fab = v.findViewById(R.id.fabH);
        if (item == null) {
            item = new Hang();
        }
        dao = new HangDao(getActivity());
        list = new ArrayList<>();
        list = dao.getAll();
        HangAdapter adapter1 = new HangAdapter(list, context, dao, this);
        rcvH.setAdapter(adapter1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext(), 0);
            }
        });
        return v;
    }

    public void xoa(final String id){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("ban co muon xoa quyen sach nay ?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                Toast.makeText(getContext(), "xoa thanh cong", Toast.LENGTH_SHORT).show();
                capNhat();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();;
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    public void openDialog(final Context context, final  int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_ud_h);
        tvName = dialog.findViewById(R.id.edNameH);
        tvPrice = dialog.findViewById(R.id.edPriceH);
        tvNum = dialog.findViewById(R.id.edNumH);
        spinner = dialog.findViewById(R.id.spLH);
        btncan = dialog.findViewById(R.id.btnDelete_H);
        btnsa = dialog.findViewById(R.id.btnUpdate_H);
        ivH = dialog.findViewById(R.id.imgH);
        ivH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickThuVienFuntion();
            }
        });
        loaiHangList = new ArrayList<LoaiHang>();
        loaiHangDAO = new LoaiHangDao(context);
        loaiHangList = (ArrayList<LoaiHang>) loaiHangDAO.getAll();
        spinerAdapter = new LoaiHangSpinnerAdapter(context, loaiHangList);
        spinner.setAdapter(spinerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               maLoaiHang  = loaiHangList.get(position).getMaLoaiHang();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
            tvName.setText("");
            tvPrice.setText("");
            tvNum.setText("");

        for (int i = 0; i < loaiHangList.size(); i++){
            if (item.getMaLoaiHang() == loaiHangList.get(i).getMaLoaiHang()){
                position = i;
            }
        }
        spinner.setSelection(position);
        btncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoa(String.valueOf(item.getMaHang()));
                dialog.dismiss();
            }
        });
        btnsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Hang();
                item.setTenHang(tvName.getText().toString());
                item.setGiaHang(Integer.parseInt(tvPrice.getText().toString()));
                item.setSoLuong(Integer.parseInt(tvNum.getText().toString()));
                item.setMaLoaiHang(maLoaiHang);
                item.setImageH(selectedImageUri.toString());
                if (validate()>0){
                    if(dao.insert(item)>0){
                        Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "that bai", Toast.LENGTH_SHORT).show();
                    }
                    capNhat();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }
    public int validate(){
        int check = 1;
        if(tvName.getText().length() == 0 || tvPrice.getText().length()==0){
            Toast.makeText(getContext(), "nhap du thong tin", Toast.LENGTH_SHORT).show();
            check =-1;
        }
        return check;
    }
    public void capNhat(){
        dao = new HangDao(getContext());
        list = dao.getAll();
        adapter = new HangAdapter(list, getContext(), dao, this);
        rcvH.setAdapter(adapter);
    }

    public void pickThuVienFuntion() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryActivityResult.launch(intent);
    }

    private ActivityResultLauncher<Intent> galleryActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        selectedImageUri = intent.getData();

                        try {
                            Picasso.get().load(selectedImageUri).placeholder(R.drawable.a).error(R.drawable.b).into(ivH);
                            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS_KEY_H, Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            if (selectedImageUri != null) {
                                editor.putString(IMAGE_URI_KEY_H, selectedImageUri.toString());
                            } else {
                                editor.remove(IMAGE_URI_KEY_H);
                            }
                            editor.apply();
                        } catch (Exception e) {
                            Log.d("TAG", "onActivityResult: Không thể load ảnh " + e.getMessage());
                        }
                    }
                }
            }
    );
}