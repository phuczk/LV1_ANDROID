package com.sonans.lv1_android.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sonans.lv1_android.database.DbHelper;
import com.sonans.lv1_android.model.NhapKho;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NhapKhoDao {
    SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public NhapKhoDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(NhapKho obj) {
        ContentValues values = new ContentValues();
        values.put("tenH", obj.getMaHangNK());
        values.put("soLuong", obj.getSoLuong());
        values.put("gia", obj.getGiaHang());
        values.put("tenVC", obj.getMaVC());
        values.put("trangThai", obj.getTrangThai());
        values.put("ngayNhap", sdf.format(obj.getNgayNhap()));
        return db.insert("NhapKho", null ,values);
    }

    public int update(NhapKho obj) {
        ContentValues values = new ContentValues();
        values.put("tenH", obj.getMaHangNK());
        values.put("soLuong", obj.getSoLuong());
        values.put("gia", obj.getGiaHang());
        values.put("tenVC", obj.getMaVC());
        values.put("trangThai", obj.getTrangThai());
        values.put("ngayNhap", sdf.format(obj.getNgayNhap()));
        return db.update("NhapKho", values ,"maNK=?", new String[]{String.valueOf(obj.getMaHangNK())});
    }

    public int delete(String id) {
        return db.delete("NhapKho", "maNK=?", new String[]{id});

    }

    @SuppressLint("Range")
    public List<NhapKho> getData(String sql, String...selectionArgs) {
        List<NhapKho> list = new ArrayList<NhapKho>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            NhapKho obj = new NhapKho();
            obj.setMaHangNK(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maNK"))));
            obj.setMaHangNK(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tenH"))));
            obj.setMaVC(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tenVC"))));
            obj.setGiaHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gia"))));
            obj.setTrangThai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("trangThai"))));
            obj.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            try{
                obj.setNgayNhap(sdf.parse(cursor.getString(cursor.getColumnIndex("ngayNhap"))));
            } catch (ParseException e){
                e.printStackTrace();
            }
            list.add(obj);
        }
        return list;
    }

    public List<NhapKho> getAll(){
        String sql = "SELECT * FROM NhapKho";
        return getData(sql);
    }

    public NhapKho getID(String id) {
        String sql = "SELECT * FROM NhapKho WHERE maNK=?";
        List<NhapKho> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(soLuong * gia) as doanhThu FROM NhapKho WHERE ngayNhap BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();

        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});

        while (c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));

            }catch (Exception e){
                Log.e("//cccccccccccccccccccccc", String.valueOf(e));
                list.add(1);
            }
        }
        // Log the sum tienThue
        return list.get(0);
    }
}
