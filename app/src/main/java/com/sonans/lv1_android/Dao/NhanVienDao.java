package com.sonans.lv1_android.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sonans.lv1_android.database.DbHelper;
import com.sonans.lv1_android.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDao {
    SQLiteDatabase db;

    public NhanVienDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(NhanVien obj) {
        ContentValues values = new ContentValues();
        values.put("tenNV", obj.getTenNhanVien());
        values.put("tuoi", obj.getTuoi());
        values.put("tdlv", obj.getThaiDoLamViec());
        return db.insert("NhanVien", null ,values);
    }

    public int update(NhanVien obj) {
        ContentValues values = new ContentValues();
        values.put("tenNV", obj.getTenNhanVien());
        values.put("tuoi", obj.getTuoi());
        values.put("tdlv", obj.getThaiDoLamViec());
        return db.update("NhanVien", values ,"maNV=?", new String[]{String.valueOf(obj.getMaNhanVien())});
    }

    public int delete(String id) {
        return db.delete("NhanVien", "maNV=?", new String[]{id});

    }

    @SuppressLint("Range")
    public List<NhanVien> getData(String sql, String...selectionArgs) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            NhanVien obj = new NhanVien();
            obj.setMaNhanVien(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maNV"))));
            obj.setTenNhanVien(cursor.getString(cursor.getColumnIndex("tenNV")));
            obj.setThaiDoLamViec(cursor.getString(cursor.getColumnIndex("tdlv")));
            obj.setTuoi(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tuoi"))));
            list.add(obj);
        }
        return list;
    }

    public List<NhanVien> getAll(){
        String sql = "SELECT * FROM NhanVien";
        return getData(sql);
    }

    public NhanVien getID(String id) {
        String sql = "SELECT * FROM NhanVien WHERE maNV=?";
        List<NhanVien> list = getData(sql, id);
        return list.get(0);
    }
}
