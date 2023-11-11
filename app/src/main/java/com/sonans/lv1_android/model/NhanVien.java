package com.sonans.lv1_android.model;

public class NhanVien {
    private int maNhanVien;
    public String tenNhanVien;
    public int tuoi;
    public String thaiDoLamViec;

    public NhanVien() {
    }

    public NhanVien(int maNhanVien, String tenNhanVien, int tuoi, String thaiDoLamViec) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.tuoi = tuoi;
        this.thaiDoLamViec = thaiDoLamViec;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getThaiDoLamViec() {
        return thaiDoLamViec;
    }

    public void setThaiDoLamViec(String thaiDoLamViec) {
        this.thaiDoLamViec = thaiDoLamViec;
    }
}
