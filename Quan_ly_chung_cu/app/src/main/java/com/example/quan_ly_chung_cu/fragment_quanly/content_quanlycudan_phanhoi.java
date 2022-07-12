package com.example.quan_ly_chung_cu.fragment_quanly;

public class content_quanlycudan_phanhoi {
    private String noidung;
    private String ngay;
    private String sonha;

    public content_quanlycudan_phanhoi(String noidung, String ngay, String sonha) {
        this.noidung = noidung;
        this.ngay = ngay;
        this.sonha = sonha;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getSonha() {
        return sonha;
    }

    public void setSonha(String sonha) {
        this.sonha = sonha;
    }
}
