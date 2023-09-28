package com.muhasebe.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Firma implements Serializable {
    private String unvan;
    private String vkNo;
    private String sifre;
    private LocalDate kayitTarihi;
    private boolean durumu=false;

    public Firma(String unvan, String vkNo, String sifre, String kayitTarihi, boolean durumu) {
        this.unvan=unvan;
        this.vkNo=vkNo;
        this.sifre=sifre;
        this.kayitTarihi=LocalDate.parse(kayitTarihi);
        this.durumu=durumu;
    }


    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getVkNo() {
        return vkNo;
    }

    public void setVkNo(String vkNo) {
        this.vkNo = vkNo;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public LocalDate getKayitTarihi() {
        return kayitTarihi;
    }

    public void setKayitTarihi(LocalDate kayitTarihi) {
        this.kayitTarihi = kayitTarihi;
    }

    public boolean isDurumu() {
        return durumu;
    }

    public void setDurumu(boolean durumu) {
        this.durumu = durumu;
    }
}
