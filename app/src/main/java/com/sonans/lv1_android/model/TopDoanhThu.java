package com.sonans.lv1_android.model;

public class TopDoanhThu {
    public String tenHang;
    public int doanhThu;

    public TopDoanhThu() {
    }

    public TopDoanhThu(String tenHang, int doanhThu) {
        this.tenHang = tenHang;
        this.doanhThu = doanhThu;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public int getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(int doanhThu) {
        this.doanhThu = doanhThu;
    }
}
