/*
TP 2
Nama        : Cheeryl Aisyah Retnowibowo
NPM         : 2206813706
Kelas       : DDP2 - D
Tanggal     : 3 Maret 2023

Kode Asdos  : AYP
Dosen       : Muhammad Hafizhuddin Hilman, S.Kom., M.Kom., Ph.D.
*/

package assignments.assignment2;

// import library java
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

public class Nota {
    // attributes
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;

    // constructor
    public Nota(int idNota, String paket, Member member, int berat, String tanggalMasuk, int sisaHariPengerjaan, boolean isReady) {
        this.idNota = idNota;
        this.paket = paket;
        this.member = member;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.sisaHariPengerjaan = sisaHariPengerjaan;
        this.isReady = isReady;
    }

    // methods
    public int getIdNota() {
        return idNota;
    }

    public String getPaket() {
        return paket;
    }    

    public Member getMember() {
        return member;
    }

    public int getBerat() {
       return berat;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan() {
        return sisaHariPengerjaan;
    }

    public void setSisaHariPengerjaan(int sisaHariPengerjaan) {
        if (this.sisaHariPengerjaan == 0) return;
        this.sisaHariPengerjaan = sisaHariPengerjaan;
        if (this.sisaHariPengerjaan == 0) {
            setIsReady(true);
        }
    }

    public boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(boolean isReady) {
       this.isReady = isReady;
    }

    /*
     * Method untuk membuat Nota
     */
    public static String generateNota(String id, String paket, int berat, String tanggalTerima, int bonusCounter){
        // starting value
        int biaya = 0;
        int durasi = 0;
        long hargaDiscount = 0;
        String tanggalSelesai = "";
        String txtDiscount = "";

        // conditions untuk menentukan biaya laundry dan durasi sesuai input user
        if (paket.equalsIgnoreCase("express")) {
            biaya = 12000;
            durasi = 1;
        }
        else if (paket.equalsIgnoreCase("fast")) {
            biaya = 10000;
            durasi = 2;
        }
        else if (paket.equalsIgnoreCase("reguler")) {
            biaya = 7000;
            durasi = 3;
        }
        
        // menghitung total biaya laundry sesuai biaya paket
        int biayaTotal = biaya * berat;

        // menghitung tanggal selesai laundry sesuai durasi paket
        try {
            SimpleDateFormat formatTanggal = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatTanggal.parse(tanggalTerima));                          // ubah string ke ke form Date
            
            calendar.add(Calendar.DATE, durasi);                                           // menambahkan hari sesuai durasi paket
            tanggalSelesai = formatTanggal.format(calendar.getTime());                     // set format tanggal selesai sesuai dengan ketentuan --> dd/MM/yyyy
        }
        catch (ParseException exception) {
        }

        // jika bonus counter == 3 --> pelanggan mendapat diskon member 50%
        if (bonusCounter == 3) {
            hargaDiscount = (biayaTotal*50)/100;
            txtDiscount = String.format(" = %d (Discount member 50%s)", hargaDiscount, "%!!!");
        }

        // me-return nota pelanggan
        return ("ID    : " + id + "\n" + 
                "Paket : " + paket + "\n" + 
                "Harga :" + "\n" +
                berat + " kg x " + biaya + " = " + biayaTotal + txtDiscount + "\n" +
                "Tanggal Terima  : " + tanggalTerima + "\n" +
                "Tanggal Selesai : " + tanggalSelesai + "\n" +
                "Status          : Belum bisa diambil :(" 
                );
    }
}
