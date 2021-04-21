package com.example.projectse.ui.home;

public class HocTuVung {
    public int idtu;
    public int idbo;
    public String dapan;
    public String dichnghia;
    public String loaitu;
    public String audio;
    public byte[] anh;


    public HocTuVung(int idtu, int idbo, String dapan, String dichnghia, String loaitu, String audio, byte[] anh) {
        this.idtu = idtu;
        this.idbo = idbo;
        this.dapan = dapan;
        this.dichnghia = dichnghia;
        this.loaitu = loaitu;
        this.audio = audio;
        this.anh = anh;
    }
}
