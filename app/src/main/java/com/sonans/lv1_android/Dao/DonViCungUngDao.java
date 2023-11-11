package com.sonans.lv1_android.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sonans.lv1_android.database.DbHelper;
import com.sonans.lv1_android.model.CungUng;
import com.sonans.lv1_android.model.DonViVanChuyen;

import java.util.ArrayList;
import java.util.List;

public class DonViCungUngDao {
    SQLiteDatabase db;

    public DonViCungUngDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(CungUng obj) {
        ContentValues values = new ContentValues();
        values.put("tenDVCU", obj.getTenDonVi());
        values.put("soDTDVCU", obj.getSdtCungUng());
        return db.insert("Dvcu", null ,values);
    }

    public int update(CungUng obj) {
        ContentValues values = new ContentValues();
        values.put("tenDVCU", obj.getTenDonVi());
        values.put("soDTDVCU", obj.getSdtCungUng());
        return db.update("Dvcu", values ,"maDVCU=?", new String[]{String.valueOf(obj.getMaCungUng())});
    }

    public int delete(String id) {
        return db.delete("Dvcu", "maDVCU=?", new String[]{id});

    }

    @SuppressLint("Range")
    public List<CungUng> getData(String sql, String...selectionArgs) {
        List<CungUng> list = new ArrayList<CungUng>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            CungUng obj = new CungUng();
            obj.setMaCungUng(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maDVCU"))));
            obj.setTenDonVi(cursor.getString(cursor.getColumnIndex("tenDVCU")));
            obj.setSdtCungUng(cursor.getString(cursor.getColumnIndex("soDTDVCU")));
            list.add(obj);
        }
        return list;
    }

    public List<CungUng> getAll(){
        String sql = "SELECT * FROM Dvcu";
        return getData(sql);
    }

    public CungUng getID(String id) {
        String sql = "SELECT * FROM Dvcu WHERE maDVCU=?";
        List<CungUng> list = getData(sql, id);
        return list.get(0);
    }
}
