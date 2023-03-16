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

// import assignments.assignment1.NotaGenerator;

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
}
