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

import assignments.assignment1.NotaGenerator;

public class Nota {
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;

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

    public boolean getIsReady() {
        return isReady;
    }

    // TODO: tambahkan attributes yang diperlukan untuk class ini
    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        // TODO: buat constructor untuk class ini
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
}
