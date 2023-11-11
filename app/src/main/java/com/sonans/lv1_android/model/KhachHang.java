package com.sonans.lv1_android.model;

public class KhachHang {
    private int maKhachHang;
    public String tenKH;
    private String sdt;

    public KhachHang(int maKhachHang, String tenKH, String sdt) {
        this.maKhachHang = maKhachHang;
        this.tenKH = tenKH;
        this.sdt = sdt;
    }

    public KhachHang() {
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
