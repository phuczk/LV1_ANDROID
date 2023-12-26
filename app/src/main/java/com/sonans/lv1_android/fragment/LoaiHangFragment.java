package com.sonans.lv1_android.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sonans.lv1_android.Dao.LoaiHangDao;
import com.sonans.lv1_android.R;
import com.sonans.lv1_android.adapter.LoaiHangAdapter;
import com.sonans.lv1_android.model.LoaiHang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LoaiHangFragment extends Fragment implements LoaiHangAdapter.ChucNangInterFace {
    private static final int PICK_IMAGE_REQUEST = 1;
    RecyclerView rcvLH;
    FloatingActionButton fab;
    LoaiHangDao dao;
    LoaiHangAdapter adapter;
    LoaiHang item;
    List<LoaiHang> list;
    public Context context;
    Dialog dialog;
    int maLoaiHang;
    public Uri selectedImageUri;
    String img;
    private LoaiHangAdapter.ChucNangInterFace chucNanginterface;
    private static final String SHARED_PREFS_KEY = "MyAppPreferences";
    private static final String IMAGE_URI_KEY = "image_uri";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loai_hang, container, false);
        rcvLH = v.findViewById(R.id.rcvLH);
        rcvLH.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab = v.findViewById(R.id.fab);
        if (item == null) {
            item = new LoaiHang();
        }
        LoaiHangDao loaiHangDao = new LoaiHangDao(getActivity());
        list = new ArrayList<>();
        list = loaiHangDao.getAll();
        adapter = new LoaiHangAdapter(list, loaiHangDao, context, this);
        rcvLH.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext(), 0);
            }
        });

        return v;
    }

    public void capNhat() {
        dao = new LoaiHangDao(getContext());
        list = dao.getAll();
        adapter = new LoaiHangAdapter(list, dao, getContext(), this);
        rcvLH.setAdapter(adapter);
    }

    EditText edNameLH, edMaLH;
    Button btnUpLH, btnDelLH;
    ImageView ivLH;

    public void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_ud_lh);
        edNameLH = dialog.findViewById(R.id.edNameLH);
        edMaLH = dialog.findViewById(R.id.edMaLH);
        ivLH = dialog.findViewById(R.id.imgLH);
        btnDelLH = dialog.findViewById(R.id.btnDelete_LH);
        btnUpLH = dialog.findViewById(R.id.btnUpdate_LH);
        ivLH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickThuVienFuntion();
            }
        });

        edMaLH.setText("");
        edNameLH.setText("");
        ivLH.setImageResource(R.drawable.b);
        btnDelLH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoa(String.valueOf(item.getMaLoaiHang()));
                dialog.dismiss();
            }
        });
        btnUpLH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new LoaiHangDao(getContext());
                item = new LoaiHang();
                item.setTenLoaiHang(edNameLH.getText().toString());
                item.setImageLH(selectedImageUri.toString());
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
        if (edNameLH.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
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
                            Picasso.get().load(selectedImageUri).placeholder(R.drawable.a).error(R.drawable.b).into(ivLH);
                            // Lưu đường dẫn của ảnh đã chọn vào SharedPreferences
                            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS_KEY, Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            if (selectedImageUri != null) {
                                editor.putString(IMAGE_URI_KEY, selectedImageUri.toString());
                            } else {
                                editor.remove(IMAGE_URI_KEY);
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