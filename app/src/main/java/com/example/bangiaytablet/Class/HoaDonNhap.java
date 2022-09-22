package com.example.bangiaytablet.Class;

public class HoaDonNhap {
    String ngayTaoHoaDon,nguoiNhap,ncc;
    int MaHoaDon;
    float tongtien;

    public HoaDonNhap(String ngayTaoHoaDon, int maHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        MaHoaDon = maHoaDon;
    }

    public HoaDonNhap(String ngayTaoHoaDon, int maHoaDon, float tongtien) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        MaHoaDon = maHoaDon;
        this.tongtien = tongtien;
    }

    public HoaDonNhap(String ngayTaoHoaDon, String nguoiNhap, String ncc, int maHoaDon, float tongtien) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.nguoiNhap = nguoiNhap;
        this.ncc = ncc;
        MaHoaDon = maHoaDon;
        this.tongtien = tongtien;
    }

    public HoaDonNhap(String ngayTaoHoaDon, String nguoiNhap, String ncc, int maHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.nguoiNhap = nguoiNhap;
        this.ncc = ncc;
        MaHoaDon = maHoaDon;
    }

    public String getNguoiNhap() {
        return nguoiNhap;
    }

    public void setNguoiNhap(String nguoiNhap) {
        this.nguoiNhap = nguoiNhap;
    }

    public String getNcc() {
        return ncc;
    }

    public void setNcc(String ncc) {
        this.ncc = ncc;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    public String getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public void setNgayTaoHoaDon(String ngayTaoHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        MaHoaDon = maHoaDon;
    }
}
