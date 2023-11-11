package com.sonans.lv1_android.model;

public class LoaiHang {
    private int maLoaiHang;
    public String tenLoaiHang;
    public String imageLH;

    public LoaiHang(int maLoaiHang, String tenLoaiHang, String imageLH) {
        this.maLoaiHang = maLoaiHang;
        this.tenLoaiHang = tenLoaiHang;
        this.imageLH = imageLH;
    }

    public LoaiHang() {
    }

    public int getMaLoaiHang() {
        return maLoaiHang;
    }

    public void setMaLoaiHang(int maLoaiHang) {
        this.maLoaiHang = maLoaiHang;
    }

    public String getTenLoaiHang() {
        return tenLoaiHang;
    }

    public void setTenLoaiHang(String tenLoaiHang) {
        this.tenLoaiHang = tenLoaiHang;
    }

    public String getImageLH() {
        return imageLH;
    }

    public void setImageLH(String imageLH) {
        this.imageLH = imageLH;
    }
}
