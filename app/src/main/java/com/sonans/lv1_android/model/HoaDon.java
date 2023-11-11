package com.sonans.lv1_android.model;

import java.util.Date;

public class HoaDon {
    private String maHoaDon;
    private int maHang;
    private int maKhachHang;
    public int soLuongHangMua;
    public Date ngayXuatHoaDon;
    public int tongTien;

    public HoaDon(String maHoaDon, int maHang, int maKhachHang, int soLuongHangMua, Date ngayXuatHoaDon, int tongTien) {
        this.maHoaDon = maHoaDon;
        this.maHang = maHang;
        this.maKhachHang = maKhachHang;
        this.soLuongHangMua = soLuongHangMua;
        this.ngayXuatHoaDon = ngayXuatHoaDon;
        this.tongTien = tongTien;
    }

    public HoaDon() {
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getSoLuongHangMua() {
        return soLuongHangMua;
    }

    public void setSoLuongHangMua(int soLuongHangMua) {
        this.soLuongHangMua = soLuongHangMua;
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
