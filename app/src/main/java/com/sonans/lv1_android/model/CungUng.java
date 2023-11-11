package com.sonans.lv1_android.model;

public class CungUng {
    private int maCungUng;
    public String tenDonVi;
    private String sdtCungUng;

    public CungUng() {
    }

    public CungUng(int maCungUng, String tenDonVi, String sdtCungUng) {
        this.maCungUng = maCungUng;
        this.tenDonVi = tenDonVi;
        this.sdtCungUng = sdtCungUng;
    }

    public int getMaCungUng() {
        return maCungUng;
    }

    public void setMaCungUng(int maCungUng) {
        this.maCungUng = maCungUng;
    }

    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    public String getSdtCungUng() {
        return sdtCungUng;
    }

    public void setSdtCungUng(String sdtCungUng) {
        this.sdtCungUng = sdtCungUng;
    }
}
