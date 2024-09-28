package com.example.luluchef.model;

public class Country {
    private String strArea;
    private int imageId;
    public Country(String strArea , int imageId) {
        this.strArea = strArea;
        this.imageId = imageId;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public int getImageId() {
        return imageId;
    }}