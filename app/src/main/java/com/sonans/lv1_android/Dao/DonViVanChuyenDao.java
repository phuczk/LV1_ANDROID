package com.sonans.lv1_android.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sonans.lv1_android.database.DbHelper;
import com.sonans.lv1_android.model.DonViVanChuyen;
import com.sonans.lv1_android.model.Hang;

import java.util.ArrayList;
import java.util.List;

public class DonViVanChuyenDao {
    SQLiteDatabase db;

    public DonViVanChuyenDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(DonViVanChuyen obj) {
        ContentValues values = new ContentValues();
        values.put("tenVC", obj.getTenDonVi());
        values.put("soDTVC", obj.getSdt());
        return db.insert("Dvvc", null ,values);
    }

    public int update(DonViVanChuyen obj) {
        ContentValues values = new ContentValues();
        values.put("tenVC", obj.getTenDonVi());
        values.put("soDTVC", obj.getSdt());
        return db.update("Dvvc", values ,"maVC=?", new String[]{String.valueOf(obj.getMaVC())});
    }

    public int delete(String id) {
        return db.delete("Dvvc", "maVC=?", new String[]{id});

    }

    @SuppressLint("Range")
    public List<DonViVanChuyen> getData(String sql, String...selectionArgs) {
        List<DonViVanChuyen> list = new ArrayList<DonViVanChuyen>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            DonViVanChuyen obj = new DonViVanChuyen();
            obj.setMaVC(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maVC"))));
            obj.setTenDonVi(cursor.getString(cursor.getColumnIndex("tenVC")));
            obj.setSdt(cursor.getString(cursor.getColumnIndex("soDTVC")));
            list.add(obj);
        }
        return list;
    }

    public List<DonViVanChuyen> getAll(){
        String sql = "SELECT * FROM Dvvc";
        return getData(sql);
    }

    public DonViVanChuyen getID(String id) {
        String sql = "SELECT * FROM Dvvc WHERE maVC=?";
        List<DonViVanChuyen> list = getData(sql, id);
        return list.get(0);
    }
}
