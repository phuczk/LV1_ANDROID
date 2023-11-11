package com.sonans.lv1_android.model;

public class Product {
    private String code;
    private String name;
    private double price;
    private String description;
    private String imagePath;

    public Product(String code, String name, double price, String imagePath) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}