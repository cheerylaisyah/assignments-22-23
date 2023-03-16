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
}
