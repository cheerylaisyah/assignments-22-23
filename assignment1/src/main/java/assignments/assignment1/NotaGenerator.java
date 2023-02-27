/*
Nama        : Cheeryl Aisyah Retnowibowo
NPM         : 2206813706
Kelas       : DDP2 - D
Tanggal     : 20 Februari 2023

Kode Asdos  : AYP
Dosen       : Muhammad Hafizhuddin Hilman, S.Kom., M.Kom., Ph.D.
*/

package assignments.assignment1;

// meng-import library java
import java.util.*; import java.text.ParseException; import java.text.SimpleDateFormat;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);
    /*
     * method main
     */
    public static void main(String[] args) {
        boolean program = true;
        while (program) {
            // mencetak pilihan menu
            printMenu();
            // user memasukkan pilihan menu
            System.out.print("Pilihan : ");
            String pilihan = input.nextLine();
            System.out.println("================================");

            // conditions sesuai pilihan menu user
            if (pilihan.equals("0")) {
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                program = false;
            }
            else if (pilihan.equals("1")) {
                // meminta input user
                System.out.println("Masukkan nama Anda: ");
                String nama = input.nextLine();

                System.out.println("Masukkan nomor handphone Anda: ");
                String nomorHP = " ";
                nomorHP = checkNomorHP(nomorHP);                                    // mem-validasi input user (nomor HP hanya boleh digit)

                String iDPelanggan = generateId(nama, nomorHP);                     // memanggil method generate ID untuk mendapatkan ID pelanggan
                
                // mencetak ID pelanggan sesuai input user
                System.out.printf("ID Anda : %s%n", iDPelanggan);
            }
            else if (pilihan.equals("2")) {
                // meminta input user
                System.out.println("Masukkan nama Anda: ");
                String nama = input.nextLine();

                System.out.println("Masukkan nomor handphone Anda: ");
                String nomorHP = " ";
                nomorHP = checkNomorHP(nomorHP);                                    // mem-validasi input user (nomor HP hanya menerima digit)

                String iDPelanggan = generateId(nama, nomorHP);                     // memanggil method generate ID untuk mendapatkan ID pelanggan
                String tanggalTerima = inputTanggalTerima();                        // memanggil method input tanggal terima
                String paketCuci = inputPaketCuci();                                // memanggil method input paket cuci
                int beratCucian = inputBeratCucian();                               // memanggil method input paket cuci

                // mencetak nota sesuai input user
                System.out.println("Nota Laundry");
                System.out.print(generateNota(iDPelanggan, paketCuci, beratCucian, tanggalTerima));
                System.out.println("");
            }
            // user memasukkan input selain yang tersedia
            else {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        input.close();
    }

    /*
     * method untuk menampilkan menu di NotaGenerator
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /*
     * method untuk menampilkan paket
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /*
     * method untuk membuat ID dari nama dan nomor handphone
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

    /*
     * method untuk membuat Nota
     */
    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // starting value
        int biaya = 0;
        int durasi = 0;
        String tanggalSelesai = "";

        // conditions untuk menentukan biaya laundry dan durasi sesuai input user
        if (paket.equals("express")) {
            biaya = 12000;
            durasi = 1;
        }
        else if (paket.equals("fast")) {
            biaya = 10000;
            durasi = 2;
        }
        else if (paket.equals("reguler")) {
            biaya = 7000;
            durasi = 3;
        }
        
        // menghitung total biaya laundry sesuai biaya paket
        int biayaTotal = biaya * berat;

        // menghitung tanggal selesai laundry sesuai durasi paket
        try {
            SimpleDateFormat formatTanggal = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(formatTanggal.parse(tanggalTerima));
            c.add(Calendar.DATE, durasi);
            tanggalSelesai = formatTanggal.format(c.getTime());
        }
        catch (ParseException e) {
        }

        // me-return nota pelanggan
        return ("ID    : " + id + "\n" + 
                "Paket : " + paket + "\n" + 
                "Harga :" + "\n" +
                berat + " kg x " + biaya + " = " + biayaTotal + "\n" +
                "Tanggal Terima  : " + tanggalTerima + "\n" +
                "Tanggal Selesai : " + tanggalSelesai
                );
    }

    /* 
     * method untuk set variable nama sesuai ketentuan (huruf kapital dan hanya kata pertama)
     */ 
    public static String checkNama(String nama) {
        nama = nama.toUpperCase();
        if (nama.contains(" ")){
            String[] namaSplited = nama.split("\\s");
            nama = namaSplited[0];
        }
        return nama;
    }
    
    /*
     * method untuk memvalidasi nomor HP (hanya menerima digit)
     */
    public static String checkNomorHP(String nomorHP) {
        boolean checkNomorHP = true;

        // loop untuk memvalidasi setiap digit nomor HP 
        while (checkNomorHP) {
            nomorHP = input.nextLine();
            for (int i = 0; i < nomorHP.length(); i++) {
                if (Character.isDigit(nomorHP.charAt(i))) {                         // jika nomor HP merupakan digit --> meminta input kembali
                    if (Character.getNumericValue(nomorHP.charAt(i)) < 0) {         // jika digit nomor HP < 0  --> meminta input kembali
                        System.out.println("Nomor hp hanya menerima digit");
                        break;
                    }
                    else {
                        checkNomorHP = false;
                    }
                }
                else {                                                              // jika nomor HP bukan digit/angka --> meminta input kembali
                    System.out.println("Nomor hp hanya menerima digit");
                    break;
                }
            }
        }
        return nomorHP;
    }

    /*
     * method untuk meminta input tanggal penerimaan laundry
     */
    public static String inputTanggalTerima() {
        System.out.println("Masukkan tanggal terima: ");
        String tanggalTerima = input.nextLine();

        return tanggalTerima;
    }

    /*
     * method untuk meminta input paket cuci yang dipilih
     */
    public static String inputPaketCuci() {
        String paketCuci = "";
        boolean checkPaketCuci = true;

        // loop untuk validasi input user (harus sesuai paket yang tersedia)
        while (checkPaketCuci) {
            System.out.println("Masukkan paket laundry: ");
            paketCuci = input.nextLine();
            String paketCuciCheck = paketCuci.toLowerCase();

            // conditions untuk validasi input user
            if (paketCuciCheck.equals("express") || paketCuciCheck.equals("fast")|| paketCuciCheck.equals("reguler") ) {
                checkPaketCuci = false;
            }
            else if (paketCuciCheck.equals("?")) {
                showPaket();
            }
            else {                                                                   // jika paket yang dipilih tidak sesuai --> meminta input kembali
                System.out.printf("Paket %s tidak diketahui%n[ketik ? untuk mencari tahu jenis paket]%n", paketCuci);
            }
        }
        return paketCuci;
    }

    /*
     * method untuk meminta input berat cucian
     */
    public static int inputBeratCucian() {
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
}
