package com.example.projectse.taikhoan;

import java.io.Serializable;

public class NguoiDung implements Serializable {
    private int iduser;
    private String HoTen;
    private String TaiKhoan;
    private String MatKhau;
    private int Point;
    private String Email;
    private String SDT;

    public NguoiDung(int iduser, String hoTen, String taiKhoan, String matKhau, int point, String email, String SDT) {
        this.iduser = iduser;
        this.HoTen = hoTen;
        this.TaiKhoan = taiKhoan;
        this.MatKhau = matKhau;
        this.Point = point;
        this.Email = email;
        this.SDT = SDT;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int point) {
        Point = point;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
