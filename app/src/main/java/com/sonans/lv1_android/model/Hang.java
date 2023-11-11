package com.sonans.lv1_android.model;

public class Hang {
    private int maHang;
    public String tenHang;
    public int soLuong;
    public int giaHang;
    private int maLoaiHang;
    public String imageH;

    public Hang(int maHang, String tenHang, int soLuong, int giaHang, int maLoaiHang, String imageH) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.giaHang = giaHang;
        this.maLoaiHang = maLoaiHang;
        this.imageH = imageH;
    }

    public Hang() {
    }

    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
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

    public int getMaLoaiHang() {
        return maLoaiHang;
    }

    public void setMaLoaiHang(int maLoaiHang) {
        this.maLoaiHang = maLoaiHang;
    }

    public String getImageH() {
        return imageH;
    }

    public void setImageH(String imageH) {
        this.imageH = imageH;
    }
}
