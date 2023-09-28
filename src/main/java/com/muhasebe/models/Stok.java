package com.muhasebe.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Stok implements Serializable {
    private long firmaId;
    private String urunAdi;
    private String barkod;
    private BigDecimal fiyat;
    private BigDecimal adet;

    public Stok(Long firmaId, String urunAdi, String barkod, BigDecimal fiyat, BigDecimal adet) {
        this.firmaId=firmaId;
        this.urunAdi=urunAdi;
        this.barkod=barkod;
        this.fiyat=fiyat;
        this.adet=adet;
    }

    public long getFirmaId() {
        return firmaId;
    }

    public void setFirmaId(long firmaId) {
        this.firmaId = firmaId;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public BigDecimal getFiyat() {
        return fiyat;
    }

    public void setFiyat(BigDecimal fiyat) {
        this.fiyat = fiyat;
    }

    public BigDecimal getAdet() {
        return adet;
    }

    public void setAdet(BigDecimal adet) {
        this.adet = adet;
    }
}
