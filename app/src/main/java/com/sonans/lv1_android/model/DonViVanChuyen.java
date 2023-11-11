package com.sonans.lv1_android.model;

public class DonViVanChuyen {
    private int maVC;
    public String tenDonVi;
    private String sdt;

    public DonViVanChuyen() {
    }

    public DonViVanChuyen(int maVC, String tenDonVi, String sdt) {
        this.maVC = maVC;
        this.tenDonVi = tenDonVi;
        this.sdt = sdt;
    }

    public int getMaVC() {
        return maVC;
    }

    public void setMaVC(int maVC) {
        this.maVC = maVC;
    }

    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
