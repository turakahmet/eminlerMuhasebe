package com.muhasebe.fileController;

import com.muhasebe.fileEnum.MuhFileDeger;
import com.muhasebe.models.Firma;
import com.muhasebe.models.Stok;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FileController {
    private List<Firma> firmaList = new ArrayList<>();
    private List<Stok> stokList = new ArrayList<>();

    public void ornekFirmaDosyasiOlustur() {
        // Örnek Firma listesi olustur methodu
        ornekFirmaListesiDoldur();
        String dosyaAdi = "firma.txt"; // Dosya adını ve yolunu istediğiniz gibi ayarlayabilirsiniz
        try (BufferedWriter dosya = new BufferedWriter(new FileWriter(dosyaAdi))) {
            int firmaId = 1;
            for (Firma firma : firmaList) {
                dosya.write(firmaId + ".");
                dosya.write(firma.getUnvan() + ",");
                dosya.write(firma.getVkNo() + ",");
                dosya.write(firma.getSifre() + ",");
                dosya.write(firma.getKayitTarihi().toString() + ",");
                dosya.write(firma.isDurumu() ? "onayli" : "pasif");
                dosya.write("\n");
                firmaId++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ornekFirmaListesiDoldur() {
        //onayli firmalar
        firmaList.add(new Firma("Ayaz inşaat A.Ş.", "1231", "1111", "2023-09-01", true));
        firmaList.add(new Firma("Öncü Kırtasiye", "1232", "2222", "2023-09-05", true));
        firmaList.add(new Firma("Ayyıldız Mobilya", "1233", "3333", "2023-09-04", true));
        //pasif firmalar
        firmaList.add(new Firma("Ersinler Makine A.Ş.", "1234", "4444", "2023-09-02", false));
        firmaList.add(new Firma("Zafer Emlak", "1235", "5555", "2023-09-04", false));
        firmaList.add(new Firma("Victorya Fırın", "1236", "6666", "2023-09-07", false));
    }


    public void ornekStokDosyasiOlustur() {
        // Örnek stok listesi olustur methodu
        ornekStokListesiDoldur();
        try (BufferedWriter dosya = new BufferedWriter(new FileWriter(MuhFileDeger.Stok.getDosyaAdi))) {
            for (Stok stok : stokList) {
                dosya.write(stok.getFirmaId() + ",");
                dosya.write(stok.getUrunAdi() + ",");
                dosya.write(stok.getBarkod() + ",");
                dosya.write(stok.getFiyat().toString() + ",");
                dosya.write(stok.getAdet().toString() + ",");
                dosya.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ornekStokListesiDoldur() {
        stokList.add(new Stok(1L, "Çimento", "1111", new BigDecimal(150), new BigDecimal(1000)));
        stokList.add(new Stok(1L, "Tuğla", "2222", new BigDecimal(50), new BigDecimal(5000)));
        stokList.add(new Stok(3L, "Sehpa", "3333", new BigDecimal(600), new BigDecimal(10)));
        stokList.add(new Stok(3L, "Masa Sandalye", "4444", new BigDecimal(5000), new BigDecimal(5)));
        stokList.add(new Stok(2L, "Defter", "5555", new BigDecimal(30), new BigDecimal(10)));
        stokList.add(new Stok(2L, "Dosya", "6666", new BigDecimal(100), new BigDecimal(45)));
        stokList.add(new Stok(6L, "Kuru Pasta", "7777", new BigDecimal(90), new BigDecimal(50)));
    }
}
