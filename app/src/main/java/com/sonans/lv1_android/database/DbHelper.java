package com.sonans.lv1_android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DAX";
    private static final int DB_VERSION = 2;
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    static final String CREATE_TABLE_LOAI_HANG =
            "create table LoaiHang ("+
                    "maLH INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenLH TEXT NOT NULL, " +
                    "imgLH TEXT)";
    static final String CREATE_TABLE_HANG =
            "create table Hang ("+
                    "maH INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenH TEXT NOT NULL, " +
                    "soLuong INTEGER NOT NULL, " +
                    "giaHang INTEGER NOT NULL, " +
                    "imgH TEXT, " +
                    "maLH INTEGER REFERENCES LoaiHang(MaLH))";

    static final String CREATE_TABLE_HOA_DON =
            "create table HoaDon ("+
                    "maHD TEXT NOT NULL, " +
                    "maH INTEGER REFERENCES Hang(MaH), " +
                    "maKH INTEGER REFERENCES KhachHang(MaKH), " +
                    "soLuong INTEGER NOT NULL, " +
                    "tongTien INTEGER NOT NULL, " +
                    "ngay DATE NOT NULL)";

    static final String CREATE_TABLE_KHACH_HANG =
            "create table KhachHang (" +
                    "maKH INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenKH TEXT NOT NULL, " +
                    "sdt TEXT NOT NULL)";

    static final String CREATE_TABLE_NHAN_VIEN =
            "create table NhanVien (" +
                    "maNV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenNV TEXT NOT NULL, " +
                    "tdlv TEXT NOT NULL, " +
                    "tuoi INTEGER NOT NULL)";
    static final String CREATE_TABLE_NHAP_KHO =
            "create table NhapKho (" +
                    "maNK INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenH TEXT NOT NULL, " +
                    "soLuong INTEGER NOT NULL, " +
                    "gia INTEGER NOT NULL, " +
                    "tenVC TEXT NOT NULL, " +
                    "ngayNhap DATE NOT NULL, " +
                    "trangThai INTEGER NOT NULL)";

    static final String CREATE_TABLE_DVVC =
            "create table Dvvc (" +
                    "maVC INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenVC TEXT NOT NULL, " +
                    "soDTVC TEXT NOT NULL)";

    static final String CREATE_TABLE_DVCU =
            "create table Dvcu (" +
                    "maDVCU INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenDVCU TEXT NOT NULL, " +
                    "soDTDVCU TEXT NOT NULL)";

    static final String CREATE_TABLE_USER =
            "create table user (" +
                    "hoTen TEXT NOT NULL, " +
                    "userName TEXT NOT NULL, " +
                    "password TEXT NOT NULL)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HANG);
        db.execSQL(CREATE_TABLE_KHACH_HANG);
        db.execSQL(CREATE_TABLE_NHAN_VIEN);
        db.execSQL(CREATE_TABLE_LOAI_HANG);
        db.execSQL(CREATE_TABLE_NHAP_KHO);
        db.execSQL(CREATE_TABLE_HOA_DON);
        db.execSQL(CREATE_TABLE_DVVC);
        db.execSQL(CREATE_TABLE_DVCU);
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropLoaiHang = "drop table if exists LoaiHang";
        db.execSQL(dropLoaiHang);
        String dropNhanVien = "drop table if exists NhanVien";
        db.execSQL(dropNhanVien);
        String dropHang = "drop table if exists Hang";
        db.execSQL(dropHang);
        String dropHoaDon = "drop table if exists HoaDon";
        db.execSQL(dropHoaDon);
        String dropNhapKho = "drop table if exists NhapKho";
        db.execSQL(dropNhapKho);
        String dropKhachHang = "drop table if exists KhachHang";
        db.execSQL(dropKhachHang);
        String dropDVVC = "drop table if exists Dvvc";
        db.execSQL(dropDVVC);
        String dropDVCU = "drop table if exists Dvcu";
        db.execSQL(dropDVCU);
        String dropUser = "drop table if exists user";
        db.execSQL(dropUser);
        onCreate(db);
    }
}
