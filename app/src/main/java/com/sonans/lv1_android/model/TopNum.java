package com.sonans.lv1_android.model;

public class TopNum {
    public String tenHang;
    public int soLuong;

    public TopNum(String tenHang, int soLuong) {
        this.tenHang = tenHang;
        this.soLuong = soLuong;
    }

    public TopNum() {
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
}
