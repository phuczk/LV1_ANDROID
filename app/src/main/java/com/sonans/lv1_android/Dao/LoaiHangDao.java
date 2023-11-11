package com.sonans.lv1_android.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sonans.lv1_android.database.DbHelper;
import com.sonans.lv1_android.model.LoaiHang;

import java.util.ArrayList;
import java.util.List;

public class LoaiHangDao {
    SQLiteDatabase db;

    public LoaiHangDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiHang obj) {
        ContentValues values = new ContentValues();
        values.put("tenLH", obj.getTenLoaiHang());
        values.put("imgLH", obj.getImageLH());
        return db.insert("LoaiHang", null ,values);
    }

    public int update(LoaiHang obj) {
        ContentValues values = new ContentValues();
        values.put("tenLH", obj.getTenLoaiHang());
        values.put("imgLH", obj.getImageLH());
        return db.update("LoaiHang", values ,"maLH=?", new String[]{String.valueOf(obj.getMaLoaiHang())});
    }

    public int delete(String id) {
        return db.delete("LoaiHang", "maLH=?", new String[]{id});

    }

    @SuppressLint("Range")
    public List<LoaiHang> getData(String sql, String...selectionArgs) {
        List<LoaiHang> list = new ArrayList<LoaiHang>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            LoaiHang obj = new LoaiHang();
            obj.setMaLoaiHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLH"))));
            obj.setTenLoaiHang(cursor.getString(cursor.getColumnIndex("tenLH")));
            obj.setImageLH(cursor.getString(cursor.getColumnIndex("imgLH")));
            list.add(obj);
        }
        return list;
    }

    public List<LoaiHang> getAll(){
        String sql = "SELECT * FROM LoaiHang";
        return getData(sql);
    }

    public LoaiHang getID(String id) {
        String sql = "SELECT * FROM LoaiHang WHERE maLH=?";
        List<LoaiHang> list = getData(sql, id);
        return list.get(0);
    }
}
