package com.example.quan_ly_chung_cu.fragment_cudan;

public class content_thongbao {
    private String noidung_thongbao;
    private String ngay_thongbao;

    public content_thongbao(String noidung_thongbao, String ngay_thongbao) {
        this.noidung_thongbao = noidung_thongbao;
        this.ngay_thongbao = ngay_thongbao;
    }

    public String getNoidung_thongbao() {
        return noidung_thongbao;
    }

    public void setNoidung_thongbao(String noidung_thongbao) {
        this.noidung_thongbao = noidung_thongbao;
    }

    public String getNgay_thongbao() {
        return ngay_thongbao;
    }

    public void setNgay_thongbao(String ngay_thongbao) {
        this.ngay_thongbao = ngay_thongbao;
    }
}
