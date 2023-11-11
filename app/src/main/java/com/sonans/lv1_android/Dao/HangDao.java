package com.sonans.lv1_android.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sonans.lv1_android.database.DbHelper;
import com.sonans.lv1_android.model.Hang;

import java.util.ArrayList;
import java.util.List;

public class HangDao {
    SQLiteDatabase db;

    public HangDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Hang obj) {
        ContentValues values = new ContentValues();
        values.put("tenH", obj.getTenHang());
        values.put("soLuong", obj.getSoLuong());
        values.put("giaHang", obj.getGiaHang());
        values.put("maLH", obj.getMaLoaiHang());
        values.put("imgH", obj.getImageH());
        return db.insert("Hang", null ,values);
    }

    public int update(Hang obj) {
        ContentValues values = new ContentValues();
        values.put("tenH", obj.getTenHang());
        values.put("soLuong", obj.getSoLuong());
        values.put("giaHang", obj.getGiaHang());
        values.put("maLH", obj.getMaLoaiHang());
        values.put("imgH", obj.getImageH());
        return db.update("Hang", values ,"maH=?", new String[]{String.valueOf(obj.getMaHang())});
    }

    public int delete(String id) {
        return db.delete("Hang", "maH=?", new String[]{id});

    }

    @SuppressLint("Range")
    public List<Hang> getData(String sql, String...selectionArgs) {
        List<Hang> list = new ArrayList<Hang>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Hang obj = new Hang();
            obj.setMaHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maH"))));
            obj.setTenHang(cursor.getString(cursor.getColumnIndex("tenH")));
            obj.setGiaHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaHang"))));
            obj.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            obj.setMaLoaiHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLH"))));
            obj.setImageH(cursor.getString(cursor.getColumnIndex("imgH")));
            list.add(obj);
        }
        return list;
    }

    public List<Hang> getAll(){
        String sql = "SELECT * FROM Hang";
        return getData(sql);
    }

    public Hang getID(String id) {
        String sql = "SELECT * FROM Hang WHERE maH=?";
        List<Hang> list = getData(sql, id);
        return list.get(0);
    }
}
