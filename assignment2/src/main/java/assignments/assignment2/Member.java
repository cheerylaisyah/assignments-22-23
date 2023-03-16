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

public class Member {
    // attributes
    private String nama;
    private String noHP;
    private String id;
    private int bonusCounter;

    
    // constructor
    public Member(String nama, String noHP, String id, int bonusCounter) {
        this.nama = nama;
        this.noHP = noHP;
        this.id = id;
        this.bonusCounter = bonusCounter;
    }

    // methods
    public String getNama() {
        return nama;
    }
    
    public String getNoHP() {
        return noHP;
    }

    public String getId() {
        return id;
    }

    public int getBonusCounter() {
        return bonusCounter;
    }

    public int setBonusCounter() {
        this.bonusCounter += 1;
        return this.bonusCounter;
    }

    public int resetBonusCounter() {
        this.bonusCounter = 0;
        return this.bonusCounter;
    }

    /* 
     * Method untuk set variable nama sesuai ketentuan (huruf kapital dan hanya kata pertama)
     */ 
    private static String checkNama(String nama) {
        nama = nama.toUpperCase();
        if (nama.contains(" ")){
            String[] namaSplited = nama.split("\\s");
            nama = namaSplited[0];
        }
        return nama;
    }

    /*
     * Method untuk membuat ID dari nama dan nomor handphone
     */
    public static String generateId(String nama, String nomorHP){
        nama = checkNama(nama);     
        String iDSementara = nama + "-" + nomorHP;

        // menghitung jumlah karakter dari ID Pelanggan
        int checkSum = 0;
        for (int i = 0; i < iDSementara.length(); i++) {
            if (Character.isLetter(iDSementara.charAt(i))) {
                checkSum += nama.charAt(i) - 'A'+1;                                 // mendapatkan nilai huruf
            }
            else if (Character.isDigit(iDSementara.charAt(i))) {
                String nomorHPString = Character.toString(iDSementara.charAt(i));
                checkSum += Integer.parseInt(nomorHPString);                        // mendapatkan nilai nomor HP
            }
            else {
                checkSum += 7;                                                      // mendapatkan nilai selain huruf dan nomor HP
            }
        }

        // menyesuaikan digit checksum sesuai ketentuan
        String checkSumString = Integer.toString(checkSum);
        if (checkSumString.length() > 2) {                                          // jika hasil checksum > 2 angka --> ambil 2 angka terakhir
            String checkSumFinal = checkSumString.substring(checkSumString.length()-2, checkSumString.length());
            checkSumString = checkSumFinal;
        }
        else if (checkSumString.length() == 1) {                                    // jika hasil checksum = 1 angka --> tambahkan 0 di depan
            checkSumString = "0" + Integer.toString(checkSum);
        }

        // membuat dan me-return ID Pelanggan
        String iDPelanggan = iDSementara + "-" + checkSumString;                    
        return iDPelanggan;
    }
}
