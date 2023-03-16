/*
TP 2
Nama        : Cheeryl Aisyah Retnowibowo
NPM         : 2206813706
Kelas       : DDP2 - D
Tanggal     : 3 Maret 2023

Kode Asdos  : AYP
Dosen       : Muhammad Hafizhuddin Hilman, S.Kom., M.Kom., Ph.D.
*/

// import library yang diperlukan
package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

public class MainMenu {
    // state variable
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaArrayList = new ArrayList<Nota>();
    private static ArrayList<Member> memberArrayList = new ArrayList<Member>();
    private static int countId = 0;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    /*
     * Method untuk generate user berdasarkan input
     */
    private static void handleGenerateUser() {
        System.out.println("Masukkan nama Anda: ");
        String nama = input.nextLine();

        System.out.println("Masukkan nomor handphone Anda: ");
        String nomorHP = " ";
        nomorHP = checkNomorHP(nomorHP);                                    // mem-validasi input user (nomor HP hanya boleh digit)

        String iDPelanggan = generateId(nama, nomorHP);                     // memanggil method generate ID untuk mendapatkan ID pelanggan
        
        int bonusCounter = 0;
        // for loop untuk mengecek apakah ID sudah terdaftar dalam daftar member
        for(Member objMember : memberArrayList) {
            if (objMember != null) {
                if(objMember.getId().equals(iDPelanggan)) {
                    System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!%n", nama, nomorHP);
                    return;
                }
            }
        }
        // add object memberBaru ke dalam daftar member jika ID belum terdaftar
        Member memberBaru = new Member(nama, nomorHP, iDPelanggan, bonusCounter);
        memberArrayList.add(memberBaru);

        // mencetak ID pelanggan sesuai input user
        System.out.printf("Berhasil membuat member dengan ID %s!%n", iDPelanggan);
    }

    /*
     * Method untuk generate user berdasarkan input
     */
    private static void handleGenerateNota() {
        // starting variable
        String paketCuci = "";
        int beratCucian = 0;
        int sisaHariPengerjaan = 0;
        int bonusCounter = 0;
        boolean isReady = false;
        Member memberPelanggan = null;
        boolean checkMember = true;

        System.out.println("Masukkan ID member: ");
        String idMember = input.nextLine();
        
        // conditions untuk mengecek keberadaan ID member yang diinput
        if(memberArrayList.size() <= 0) {                                       // jika memberArrayList masih kosong
            checkMember = false;
            System.out.printf("Member dengan ID %s tidak ditemukan!%n", idMember);
        }
        else {
            // for loop untuk mencari ID member yang diinput dalam memberArrayList
            for(Member objMember : memberArrayList) {
                if (objMember != null) {
                    if(!objMember.getId().equals(idMember)) {
                        checkMember = false;
                        System.out.printf("Member dengan ID %s tidak ditemukan!%n", idMember);
                        return;
                    }
                    else if(objMember.getId().equals(idMember)) {
                        memberPelanggan = objMember;
                        if (objMember.getBonusCounter() < 3) {                  // jika bonus counter <3 --> akan ditambah 1 setiap pemesanan
                            bonusCounter = memberPelanggan.setBonusCounter();
                        }
                        else if (objMember.getBonusCounter() == 3) {            // jika bonus counter == 3 --> akan direset ke 0
                            bonusCounter = memberPelanggan.resetBonusCounter();
                        }
                    }
                }
            }
        }

        // jika member sudah terdaftar di memberArrayList
        if (checkMember == true) {
            paketCuci = inputPaketCuci();                                       // memanggil method input paket cuci
            beratCucian = inputBeratCucian();                                   // memanggil method input paket cuci
        }
        
        // menentukan sisaHariPengerjaan sesuai paket cuci yang dipilih
        if (paketCuci.equalsIgnoreCase("express")) {
            sisaHariPengerjaan = 1;
        }
        else if (paketCuci.equalsIgnoreCase("fast")) {
            sisaHariPengerjaan = 2;
        }
        else if (paketCuci.equalsIgnoreCase("reguler")) {
            sisaHariPengerjaan = 3;
        }

        System.out.println("Berhasil menambahkan nota!");
        System.out.printf("[ID Nota = %d]%n", countId);

        // membuat object notaBaru dan menambahkannya ke notaArrayList
        Nota notaBaru = new Nota(countId, paketCuci, memberPelanggan, beratCucian, fmt.format(cal.getTime()), sisaHariPengerjaan, isReady);
        notaArrayList.add(notaBaru);

        countId += 1;
        bonusCounter = memberPelanggan.getBonusCounter();
        System.out.println(generateNota(idMember, paketCuci, beratCucian, fmt.format(cal.getTime()), bonusCounter));
    }

    /*
     * Method untuk membuat Nota
     */
    private static String generateNota(String id, String paket, int berat, String tanggalTerima, int bonusCounter){
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

    /*
     * Method untuk handle list nota
     */
    private static void handleListNota() {
        int banyakNota = notaArrayList.size();                                             // menentukan banyak nota berdasarkan panjang notaArrayList
        System.out.printf("Terdaftar %d nota dalam sistem.%n", banyakNota);

        // jika banyak nota > 0 --> akan mengecek status kesiapan setiap pesanan
        if (banyakNota > 0) {
            for(Nota objNota : notaArrayList) {
                if(objNota != null) {
                    if(objNota.getSisaHariPengerjaan() == 0) {
                        System.out.printf("- [%d] Status      	: Sudah dapat diambil!%n", objNota.getIdNota());
                    }
                    else {
                        System.out.printf("- [%d] Status      	: Belum bisa diambil!%n", objNota.getIdNota());
                    }
                }
            }
        }
    }

    /*
     * Method untuk handle list user
     */
    private static void handleListUser() {                                             
        int banyakMember = memberArrayList.size();                                         // menentukan banyak member berdasarkan panjang memberArrayList
        System.out.printf("Terdaftar %d member dalam sistem.%n", banyakMember);
        
        // jika banyak member > 0 --> akan mencetak id dan nama setiap member
        if (banyakMember > 0) {
            for(Member objMember : memberArrayList) {
                if (objMember != null) {
                   System.out.printf("- %s : %s%n", objMember.getId(), objMember.getNama());
                }
            }
        }
    }

    /*
     * Method untuk mengambil cucian
     */
    private static void handleAmbilCucian() {
        int idNota = inputIdNota();                                             // memanggil method input ID nota
        
        // for loop untuk mengecek keberadaan ID nota yang diinput dalam notaArrayList
        for (Nota objNota : notaArrayList) {
            if (objNota.getIdNota() == idNota) {
                if(objNota.getIsReady()) {
                    System.out.printf("Nota dengan ID %d berhasil diambil!%n", idNota);
                    notaArrayList.remove(objNota);
                    return;
                }
                else {
                    System.out.printf("Nota dengan ID %d gagal diambil:(%n", idNota);
                    return;
                }
            }
        }
        System.out.printf("Nota dengan ID %d tidak ditemukan!%n", idNota);
    }

    /*
     * Method untuk menambah hari dalam sistem
     */
    private static void handleNextDay() {
        cal.add(Calendar.DATE, 1);                                              // menambahkan 1 hari dalam sistem setiap method dipanggil
        System.out.println("Dek Depe tidur hari ini... zzz...");
        
        // for loop untuk mengurangi sisaHariPengerjaan
        for(Nota objNota : notaArrayList) {
            objNota.setSisaHariPengerjaan(objNota.getSisaHariPengerjaan()-1);
            // mengecek kesiapan pesanan
            if(objNota.getIsReady()) {
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!%n", objNota.getIdNota());
            }
        }
        System.out.println("Selamat pagi dunia!");
        System.out.printf("Dek Depe: It's CuciCuci Time.%n");
    }

    /*
     * Method untuk menampilkan menu
     */
    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

    /*
     * Method untuk menampilkan paket
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
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
    private static String generateId(String nama, String nomorHP){
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

    /*
     * Method untuk memvalidasi nomor HP (hanya menerima digit)
     */
    private static String checkNomorHP(String nomorHP) {
        boolean checkNomorHP = true;
    
        // loop untuk memvalidasi setiap digit nomor HP 
        while (checkNomorHP) {
            nomorHP = input.nextLine();     
            for (int i = 0; i < nomorHP.length(); i++) {
                if (Character.isDigit(nomorHP.charAt(i))) {  
                    checkNomorHP = false;                                               // jika nomor HP merupakan digit 
                }
                else {                                                                 
                    checkNomorHP = true;                                                // jika nomor HP bukan digit/angka --> meminta input kembali
                    System.out.printf("Field nomor hp hanya menerima digit.%n");
                    break;
                }
            }
        }
        return nomorHP;
    }

    /*
     * Method untuk meminta input paket cuci yang dipilih
     */
    private static String inputPaketCuci() {
        String paketCuci = "";
        boolean checkPaketCuci = true;

        // loop untuk validasi input user (harus sesuai paket yang tersedia)
        while (checkPaketCuci) {
            System.out.println("Masukkan paket laundry: ");
            paketCuci = input.nextLine();

            // conditions untuk validasi input user
            if (paketCuci.equalsIgnoreCase("express") || paketCuci.equalsIgnoreCase("fast")|| paketCuci.equalsIgnoreCase("reguler") ) {
                checkPaketCuci = false;
            }
            else if (paketCuci.equals("?")) {
                showPaket();
            }
            else {                                                                   // jika paket yang dipilih tidak sesuai --> meminta input kembali
                System.out.printf("Paket %s tidak diketahui%n[ketik ? untuk mencari tahu jenis paket]%n", paketCuci);
            }
        }
        return paketCuci;
    }

    /*
     * Method untuk meminta input berat cucian
     */
    private static int inputBeratCucian() {
        System.out.println("Masukkan berat cucian Anda [Kg]: ");
        int beratCucian = 0;
        boolean checkBeratCucian = true;

        // loop untuk validasi input user (harus bilangan positif)
        while (checkBeratCucian) {
            try {
                beratCucian = input.nextInt();
                if (beratCucian > 0) {
                    if (beratCucian < 2) {                                          // jika berat cucian < 2 kg --> dianggap 2 kg
                        beratCucian = 2;
                        System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                    }
                    checkBeratCucian = false;
                }
                else {                                                              // input user tidak sesuai ketentuan --> meminta input kembali
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                }
            }
            catch (InputMismatchException exception) {                              // input user tidak sesuai ketentuan --> meminta input kembali
                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                input.next();
            }
        }
        input.nextLine();
        return beratCucian;
    }

    /*
     * Method untuk meminta input ID Nota
     */
    private static int inputIdNota() {
        System.out.println("Masukkan ID nota yang akan diambil: ");
        int idNota = 0;
        boolean checkIdNota = true;

        // loop untuk validasi input user (harus bilangan positif)
        while (checkIdNota) {
            try {
                idNota = input.nextInt();
                if (idNota < 0) {                                                   // input user tidak sesuai ketentuan --> meminta input kembali
                    System.out.println("ID nota berbentuk angka positif!");
                }
                else {
                    checkIdNota = false;
                }
                
            }
            catch (InputMismatchException exception) {                              // input user tidak sesuai ketentuan --> meminta input kembali
                System.out.println("ID nota berbentuk angka!");
                input.next();
            }
        }
        input.nextLine();
        return idNota;
    }
}
