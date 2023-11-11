package com.sonans.lv1_android.model;

import java.util.Date;

public class HoaDonNhap {
    private int maDN;
    public int maH;
    public int maVC;
    public int soLuong;
    public Date ngayXuatHoaDon;
    public int tongTien;

    public HoaDonNhap() {
    }

    public HoaDonNhap(int maDN, int maH, int maVC, int soLuong, Date ngayXuatHoaDon, int tongTien) {
        this.maDN = maDN;
        this.maH = maH;
        this.maVC = maVC;
        this.soLuong = soLuong;
        this.ngayXuatHoaDon = ngayXuatHoaDon;
        this.tongTien = tongTien;
    }

    public int getMaDN() {
        return maDN;
    }

    public void setMaDN(int maDN) {
        this.maDN = maDN;
    }

    public int getMaH() {
        return maH;
    }

    public void setMaH(int maH) {
        this.maH = maH;
    }

    public int getMaVC() {
        return maVC;
    }

    public void setMaVC(int maVC) {
        this.maVC = maVC;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayXuatHoaDon() {
        return ngayXuatHoaDon;
    }

    public void setNgayXuatHoaDon(Date ngayXuatHoaDon) {
        this.ngayXuatHoaDon = ngayXuatHoaDon;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
