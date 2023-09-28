package com.muhasebe.main;

import com.muhasebe.fileController.FileController;
import com.muhasebe.fileController.KullaniciIslemKontroller;
import com.muhasebe.fileEnum.MuhFileDeger;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("___________________________________________________");
        System.out.println("***/ Eminler Muhasebe Sistemine Hoş Geldiniz \\***");
        System.out.println("___________________________________________________\n");
        System.out.println("# Yapmak istediğiniz işlemi menüden işlem numarası ile seçiniz,\n");
        System.out.println("1. Firma Girişi");
        System.out.println("2. Firma Kaydı");
        System.out.println("3. Çıkış");
        ornekFirmaListesiHazirla();
        ornekStokListesiHazirla();

        KullaniciIslemKontroller kullaniciIslemi =new KullaniciIslemKontroller();
        kullaniciIslemi.calistir();
    }

    private static void ornekFirmaListesiHazirla() {
        FileController fileController=new FileController();
        //burada firma dosyası dizinde olusmuş mu oluşmamış ise oluştur diyoruz
        File firmaDosyasi = new File(MuhFileDeger.Firma.getDosyaAdi);
        if(!firmaDosyasi.exists()){
            fileController.ornekFirmaDosyasiOlustur();
        }
    }
    private static void ornekStokListesiHazirla() {
        FileController fileController=new FileController();
        //burada stok dosyası dizinde olusmuş mu oluşmamış ise oluştur diyoruz
        File firmaDosyasi = new File(MuhFileDeger.Stok.getDosyaAdi);
        if(!firmaDosyasi.exists()){
            fileController.ornekStokDosyasiOlustur();
        }
    }

}