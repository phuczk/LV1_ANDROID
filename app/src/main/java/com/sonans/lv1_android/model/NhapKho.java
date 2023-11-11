package com.sonans.lv1_android.model;

import java.util.Date;

public class NhapKho {
    private int maNK;
    private int maHangNK;
    public int soLuong;
    public int giaHang;
    private int maVC;
    public int trangThai;
    public Date ngayNhap;
    public int maCungUng;

    public NhapKho() {
    }

    public NhapKho(int maNK, int maHangNK, int soLuong, int giaHang, int maVC, int trangThai, Date ngayNhap, int maCungUng) {
        this.maNK = maNK;
        this.maHangNK = maHangNK;
        this.soLuong = soLuong;
        this.giaHang = giaHang;
        this.maVC = maVC;
        this.trangThai = trangThai;
        this.ngayNhap = ngayNhap;
        this.maCungUng = maCungUng;
    }

    public int getMaNK() {
        return maNK;
    }

    public void setMaNK(int maNK) {
        this.maNK = maNK;
    }

    public int getMaHangNK() {
        return maHangNK;
    }

    public void setMaHangNK(int maHangNK) {
        this.maHangNK = maHangNK;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaHang() {
        return giaHang;
    }

    public void setGiaHang(int giaHang) {
        this.giaHang = giaHang;
    }

    public int getMaVC() {
        return maVC;
    }

    public void setMaVC(int maVC) {
        this.maVC = maVC;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getMaCungUng() {
        return maCungUng;
    }

    public void setMaCungUng(int maCungUng) {
        this.maCungUng = maCungUng;
    }
}
