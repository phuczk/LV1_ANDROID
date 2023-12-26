package com.sonans.lv1_android.model;

public class User {
    public String hoTen;
    private String userName;
    private String password;

    public User(String hoTen, String userName, String password) {
        this.hoTen = hoTen;
        this.userName = userName;
        this.password = password;
    }

    public User() {
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
