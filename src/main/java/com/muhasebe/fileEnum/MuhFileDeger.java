package com.muhasebe.fileEnum;

public enum MuhFileDeger {
    Firma("firma.txt"),
    Stok("stok.txt"),
    Satis("satis.txt");

    public String getDosyaAdi;

    MuhFileDeger(String label) {
        this.getDosyaAdi = label;
    }
}
