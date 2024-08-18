package com.myname.bt2;

public class NhanVien {
    private String maNV;
    private String maPhongBan;
    private String tenNV;
    private int tuoi;

    public NhanVien(String maNV, String maPhongBan, String tenNV, int tuoi) {
        this.maNV = maNV;
        this.maPhongBan = maPhongBan;
        this.tenNV = tenNV;
        this.tuoi = tuoi;
    }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getMaPhongBan() { return maPhongBan; }
    public void setMaPhongBan(String maPhongBan) { this.maPhongBan = maPhongBan; }

    public String getTenNV() { return tenNV; }
    public void setTenNV(String tenNV) { this.tenNV = tenNV; }

    public int getTuoi() { return tuoi; }
    public void setTuoi(int tuoi) { this.tuoi = tuoi; }
}

