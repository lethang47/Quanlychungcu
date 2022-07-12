package com.example.quan_ly_chung_cu.fragment_cudan;

public class content_dichvu_beboi {
    private String date;
    private int image;
    public content_dichvu_beboi(String date, int image) {
        this.date = date;
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
