package com.sonans.lv1_android.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sonans.lv1_android.database.DbHelper;
import com.sonans.lv1_android.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDao {
    SQLiteDatabase db;

    public KhachHangDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("tenKH", obj.getTenKH());
        values.put("sdt", obj.getSdt());
        return db.insert("KhachHang", null ,values);
    }

    public int update(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("tenKH", obj.getTenKH());
        values.put("sdt", obj.getSdt());
        return db.update("KhachHang", values ,"maKH=?", new String[]{String.valueOf(obj.getMaKhachHang())});
    }

    public int delete(String id) {
        return db.delete("KhachHang", "maKH=?", new String[]{id});

    }

    @SuppressLint("Range")
    public List<KhachHang> getData(String sql, String...selectionArgs) {
        List<KhachHang> list = new ArrayList<KhachHang>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            KhachHang obj = new KhachHang();
            obj.setMaKhachHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH"))));
            obj.setTenKH(cursor.getString(cursor.getColumnIndex("tenKH")));
            obj.setSdt(cursor.getString(cursor.getColumnIndex("sdt")));
            list.add(obj);
        }
        return list;
    }

    public List<KhachHang> getAll(){
        String sql = "SELECT * FROM KhachHang";
        return getData(sql);
    }

    public KhachHang getID(String id) {
        String sql = "SELECT * FROM KhachHang WHERE maKH=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(0);
    }
}
