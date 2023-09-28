package com.muhasebe.fileController;

import com.muhasebe.fileEnum.MuhFileDeger;
import com.muhasebe.main.Main;
import com.muhasebe.models.Firma;
import com.muhasebe.models.Stok;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KullaniciIslemKontroller implements IKullaniciIslemleri {
    private Scanner scanner = new Scanner(System.in);

    public void calistir() {
        System.out.print(">Kullanıcı girişi:");
        int menuGirisId = Integer.parseInt(scanner.nextLine());
        String vkno;
        String pw;
        if (menuGirisId == 1) {
            System.out.println("Vergi Kimlik No: ");
            System.out.print(">Kullanıcı girişi:");
            vkno = scanner.nextLine();
            System.out.println("Şifre: ");
            System.out.print(">Kullanıcı girişi:");
            pw = scanner.nextLine();
            if (vkno.equals("admin") && pw.equals("admin")) {
                adminKullanici();
            } else {
                Long firmaId = kullaniciValidation(vkno, pw);
                normalKullanici(firmaId);
            }
        } else if (menuGirisId == 2) {
            String unvanKayit;
            String vknoKayit;
            String pwkayit;
            System.out.println("Firma Ünvanı: ");
            System.out.print(">Kullanıcı girişi:");
            unvanKayit = scanner.nextLine();
            System.out.println("Vergi Kimlik No: ");
            System.out.print(">Kullanıcı girişi:");
            vknoKayit = scanner.nextLine();
            System.out.println("Şifre: ");
            System.out.print(">Kullanıcı girişi:");
            pwkayit = scanner.nextLine();
            int sonuncuSatirSayisi=dosyaSatirSayisiBul(MuhFileDeger.Firma.getDosyaAdi);
            try (BufferedWriter dosya = new BufferedWriter(new FileWriter(MuhFileDeger.Firma.getDosyaAdi, new File(MuhFileDeger.Firma.getDosyaAdi).exists()))) {
                dosya.write(sonuncuSatirSayisi + ".");
                dosya.write(unvanKayit+",");
                dosya.write(vknoKayit+",");
                dosya.write(pwkayit+",");
                Date simdikiTarih = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String tarih = sdf.format(simdikiTarih);
                dosya.write(tarih+",");
                dosya.write("pasif\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("________________________________________________");
            System.out.print("Firma Kaydı Oluşturuldu. Yönetici onayından sonra giriş yapabilirsiniz. ");

        } else if (menuGirisId == 3) {
            System.out.println("İyi günler dileriz...");
            System.exit(1);
        } else {
            System.out.println("Hatalı giriş! Program Durduruldu");
            System.exit(1);
        }
    }

    private int dosyaSatirSayisiBul(String getDosyaAdi) {
        int satirSayisi=1;
        try (FileReader fileReader = new FileReader(getDosyaAdi);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String satir;
            while ((satir = bufferedReader.readLine()) != null) {
                satirSayisi++;
            }
            return satirSayisi;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void normalKullanici(Long firmaId) {
        scanner = new Scanner(System.in);
        System.out.println("#Yapmak istediğiniz işlemi menüden işlem numarası ile seçiniz,\n");
        System.out.println("1. Stok Girişi");
        System.out.println("2. Satış Yap ");
        System.out.println("3. Hesap özeti");
        System.out.println("4. Ana menüye dön");
        int menuGirisId = Integer.parseInt(scanner.nextLine());
        if (menuGirisId == 1) {
            String urunAdi;
            String barkod;
            BigDecimal fiyat;
            BigDecimal adet;
            System.out.println("Ürün Adı:");
            System.out.print(">Kullanıcı girişi:");
            urunAdi = scanner.nextLine();
            System.out.println("Barkod No:");
            System.out.print(">Kullanıcı girişi:");
            barkod = scanner.nextLine();
            System.out.println("Adet Fiyat:");
            System.out.print(">Kullanıcı girişi:");
            fiyat = scanner.nextBigDecimal();
            System.out.println("Ürün Adeti:");
            System.out.print(">Kullanıcı girişi:");
            adet = scanner.nextBigDecimal();
            Stok stok = new Stok(firmaId, urunAdi, barkod, fiyat, adet);
            //burada dosyası dizinde olusmuş mu oluşmamış ise oluştur diyoruz
            //new File(MuhFileDeger.Stok.getDosyaAdi).exists()
            // Dosyanın sonuna eklemek için "true"
            try (BufferedWriter dosya = new BufferedWriter(new FileWriter(MuhFileDeger.Stok.getDosyaAdi, new File(MuhFileDeger.Stok.getDosyaAdi).exists()))) {
                dosya.write(firmaId + ",");
                dosya.write(stok.getUrunAdi() + ",");
                dosya.write(stok.getBarkod() + ",");
                dosya.write(stok.getFiyat() + ",");
                dosya.write(stok.getAdet() + ",");
                dosya.write("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("________________________________________________");
            System.out.println("#Ürün ekleme başarılı");
            normalKullanici(firmaId);
        } else if (menuGirisId == 2) {
            String musteriNo;
            String barkod;
            BigDecimal fiyat;
            BigDecimal adet;
            System.out.println("Müşteri VKNO:");
            System.out.print(">Kullanıcı girişi:");
            musteriNo = scanner.nextLine();
            System.out.println("Barkod No:");
            System.out.print(">Kullanıcı girişi:");
            barkod = scanner.nextLine();
            System.out.println("Ürün Fiyat:");
            System.out.print(">Kullanıcı girişi:");
            fiyat = scanner.nextBigDecimal();
            System.out.println("Ürün Adeti:");
            System.out.print(">Kullanıcı girişi:");
            adet = scanner.nextBigDecimal();

            try (BufferedWriter dosya = new BufferedWriter(new FileWriter(MuhFileDeger.Satis.getDosyaAdi, new File(MuhFileDeger.Satis.getDosyaAdi).exists()))) {
                dosya.write(musteriNo + ",");
                dosya.write(barkod + ",");
                dosya.write(fiyat + ",");
                dosya.write(adet + ",");
                dosya.write("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //stok dosyasında stok azaltımı yapıyoruz satış kadar
            String dosyaAdi = MuhFileDeger.Stok.getDosyaAdi;
            String geciciDosyaAdi = "gecici_firma.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(geciciDosyaAdi))) {
                String satir;
                while ((satir = reader.readLine()) != null) {
                    // Satırı ayırma
                    String fileId = satir.split("\\.")[0];
                    String[] parcalar = satir.split(",");
                    // ID'yi kontrol et
                    if (parcalar[2].equals(barkod)) {
                        // "pasif" yazısını "onayli" ile değiştir
                        parcalar[parcalar.length - 1] = new BigDecimal(parcalar[parcalar.length - 1]).subtract(adet).toString();
                        satir = String.join(",", parcalar); // Parçaları tekrar birleştir
                    }
                    // Güncellenmiş satırı yeni dosyaya yaz
                    writer.write(satir);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Eski dosyayı sil
            File eskiDosya = new File(dosyaAdi);
            if (eskiDosya.delete()) {
                // Eğer silme işlemi başarılıysa yeni dosyayı eski dosyanın adıyla yeniden adlandır
                File yeniDosya = new File(geciciDosyaAdi);
                yeniDosya.renameTo(eskiDosya);
            } else {
                System.err.println("Dosya güncelleme işlemi sırasında bir hata oluştu.");
            }
            System.out.println("________________________________________________");
            System.out.println("#Ürün satışı başarılı");

            normalKullanici(firmaId);
        } else if (menuGirisId == 3) {
            HashMap<String, String> barkodUrunFiyat = new HashMap<>();

            try (FileReader fileReader = new FileReader(MuhFileDeger.Stok.getDosyaAdi);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String satir;
                while ((satir = bufferedReader.readLine()) != null) {
                    if (String.valueOf(firmaId).equals(satir.split(",")[0])) {
                        barkodUrunFiyat.put(satir.split(",")[2], satir.split(",")[3]);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (FileReader fileReader = new FileReader(MuhFileDeger.Satis.getDosyaAdi);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String satir;
                BigDecimal toplamKar = BigDecimal.ZERO;
                while ((satir = bufferedReader.readLine()) != null) {
                    for (Map.Entry<String, String> stokFiyat : barkodUrunFiyat.entrySet()) {
                        if (satir.split(",")[1].equals(stokFiyat.getKey())) {
                            String urunbarkod = stokFiyat.getKey();
                            BigDecimal urunSatisFiyat = new BigDecimal(satir.split(",")[2]);
                            BigDecimal urunMaliyetFiyat = new BigDecimal(stokFiyat.getValue());
                            BigDecimal urunSatisMiktari = new BigDecimal(satir.split(",")[3]);
                            BigDecimal urunKar = urunSatisFiyat.subtract(urunMaliyetFiyat).multiply(urunSatisMiktari);
                            System.out.println("Ürün Barkod : " + urunbarkod + "-> Ürün Net Kar :" + urunKar);
                            toplamKar = toplamKar.add(urunKar);
                        }
                    }
                }
                System.out.println("Toplam ürün karı:" + toplamKar);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (menuGirisId == 4) {
            Main.main(null);
        }
    }

    @Override
    public void adminKullanici() {
        System.out.println("# Yönetici Modu ile girildi#\nYapmak istediğiniz işlemi menüden işlem numarası ile seçiniz,\n");
        System.out.println("1. Aktif Firma Listesi");
        System.out.println("2. Onay Bekleyen Firma");
        System.out.println("3. Üst Menüye Dön");
        System.out.print(">Kullanıcı girişi:");
        int menuGirisId = Integer.parseInt(scanner.nextLine());
        if (menuGirisId == 1) {
            System.out.println("#Aktif firma listesi,");

            try (FileReader fileReader = new FileReader(MuhFileDeger.Firma.getDosyaAdi);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String satir;
                while ((satir = bufferedReader.readLine()) != null) {
                    // onayli firmalarin ekrana yazirilmasi
                    if (satir.split(",")[4].equals("onayli")) {
                        System.out.println(satir);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (menuGirisId == 2) {
            System.out.println("#Pasif firma listesi,");

            try (FileReader fileReader = new FileReader(MuhFileDeger.Firma.getDosyaAdi);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String satir;
                int pasifFirmaSayisi = 0;
                while ((satir = bufferedReader.readLine()) != null) {
                    // onayli firmalarin ekrana yazirilmasi
                    if (satir.split(",")[4].equals("pasif")) {
                        System.out.println(satir);
                        pasifFirmaSayisi++;
                    }
                }
                if (pasifFirmaSayisi == 0) {
                    System.out.println("________________________________________________");
                    System.out.println("#Onaylanacak firma yok!");
                    adminKullanici();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("________________________________________________");
            System.out.println("#Onaylamak istediğiniz firmayı numrasını giriniz,");

            System.out.print(">Kullanıcı girişi:");
            int onaylanacakFirmaNo = Integer.parseInt(scanner.nextLine());
            //burada veri kaybını engellemek için geçici bir file oluşturduk
            String dosyaAdi = MuhFileDeger.Firma.getDosyaAdi;
            String geciciDosyaAdi = "gecici_firma.txt";
            // Kullanıcıdan alınan ID'yi burada kullanabilirsiniz
            try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(geciciDosyaAdi))) {

                String satir;
                while ((satir = reader.readLine()) != null) {
                    // Satırı ayırma
                    String fileId = satir.split("\\.")[0];
                    String[] parcalar = satir.split(",");
                    // ID'yi kontrol et
                    if (fileId.equals(String.valueOf(onaylanacakFirmaNo))) {
                        // "pasif" yazısını "onayli" ile değiştir
                        parcalar[parcalar.length - 1] = "onayli";
                        satir = String.join(",", parcalar); // Parçaları tekrar birleştir
                        System.out.println(parcalar[0].split("\\.")[1] + " firması onaylandı.");
                    }
                    // Güncellenmiş satırı yeni dosyaya yaz
                    writer.write(satir);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Eski dosyayı sil
            File eskiDosya = new File(dosyaAdi);
            if (eskiDosya.delete()) {
                // Eğer silme işlemi başarılıysa yeni dosyayı eski dosyanın adıyla yeniden adlandır
                File yeniDosya = new File(geciciDosyaAdi);
                yeniDosya.renameTo(eskiDosya);
            } else {
                System.err.println("Dosya güncelleme işlemi sırasında bir hata oluştu.");
            }

        } else if (menuGirisId == 3) {
            Main.main(null);
        } else {
            System.out.println("Hatalı giriş! Program Durduruldu");
            System.exit(1);
        }
        System.out.println("________________________________________________");
        adminKullanici();

    }

    private Long kullaniciValidation(String vkno, String pw) {
        try (FileReader fileReader = new FileReader(MuhFileDeger.Firma.getDosyaAdi);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String satir;
            while ((satir = bufferedReader.readLine()) != null) {
                // onayli firmalarin ekrana yazirilmasi
                if (satir.split(",")[1].equals(vkno) && satir.split(",")[2].equals(pw) && satir.split(",")[4].equals("onayli")) {
                    System.out.println(satir.split("\\.", 2)[1].split(",")[0] + " hoş geldiniz");
                    return Long.parseLong(satir.split("\\.", 2)[0]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hatalı vergi kimlik no yada şifre tekrar deneyiniz.");
        Main.main(null);
        return null;
    }
}
