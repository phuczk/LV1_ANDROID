package com.sonans.lv1_android.model;

public class User {
    private int maUser;
    public String hoTen;
    private String userName;
    private String password;

    public User(int maUser, String hoTen, String userName, String password) {
        this.maUser = maUser;
        this.hoTen = hoTen;
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
