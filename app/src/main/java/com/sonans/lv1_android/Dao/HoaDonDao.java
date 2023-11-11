package com.sonans.lv1_android.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sonans.lv1_android.database.DbHelper;
import com.sonans.lv1_android.model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
    SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public HoaDonDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(HoaDon obj) {
        ContentValues values = new ContentValues();
        values.put("maHD", obj.getMaHoaDon());
        values.put("maH", obj.getMaHang());
        values.put("maKH", obj.getMaKhachHang());
        values.put("soLuong", obj.getSoLuongHangMua());
        values.put("tongTien", obj.getTongTien());
        values.put("ngay", sdf.format(obj.getNgayXuatHoaDon()));
        return db.insert("HoaDon", null ,values);
    }

    public int update(HoaDon obj) {
        ContentValues values = new ContentValues();
        values.put("maHD", obj.getMaHoaDon());
        values.put("maH", obj.getMaHang());
        values.put("maKH", obj.getMaKhachHang());
        values.put("soLuong", obj.getSoLuongHangMua());
        values.put("tongTien", obj.getTongTien());
        values.put("ngay", sdf.format(obj.getNgayXuatHoaDon()));
        return db.update("HoaDon", values ,"maHD=?", new String[]{String.valueOf(obj.getMaHoaDon())});
    }

    public int delete(String id) {
        return db.delete("HoaDon", "maHD=?", new String[]{id});

    }

    @SuppressLint("Range")
    public List<HoaDon> getData(String sql, String...selectionArgs) {
        List<HoaDon> list = new ArrayList<HoaDon>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            HoaDon obj = new HoaDon();
            obj.setMaHoaDon(cursor.getString(cursor.getColumnIndex("maHD")));
            obj.setMaHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maH"))));
            obj.setMaKhachHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH"))));
            obj.setSoLuongHangMua(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            obj.setTongTien(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tongTien"))));
            try{
                obj.setNgayXuatHoaDon(sdf.parse(cursor.getString(cursor.getColumnIndex("ngay"))));
            } catch (ParseException e){
                e.printStackTrace();
            }
            list.add(obj);
        }
        return list;
    }

    public List<HoaDon> getAll(){
        String sql = "SELECT * FROM HoaDon";
        return getData(sql);
    }

    public HoaDon getID(String id) {
        String sql = "SELECT * FROM HoaDon WHERE maHD=?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(tongTien) as doanhThu FROM HoaDon WHERE ngay BETWEEN ? AND ?";
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
