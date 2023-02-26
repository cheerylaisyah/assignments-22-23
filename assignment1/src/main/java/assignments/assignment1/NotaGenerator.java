/*
Nama        : Cheeryl Aisyah Retnowibowo
NPM         : 2206813706
Kelas       : DDP2 - D
Tanggal     : 20 Februari 2023

Kode Asdos  : AYP
Dosen       : Muhammad Hafizhuddin Hilman, S.Kom., M.Kom., Ph.D.
*/

package assignments.assignment1;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);
    
    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        // TODO: Implement interface menu utama
        boolean program = true;
        while (program) {
            printMenu();
            System.out.print("Pilihan : ");
            String pilihan = input.nextLine();

            if (pilihan.equals("0")) {
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                program = false;
            }
            else if (pilihan.equals("1")) {
                String nama = inputNama();
                String nomorHP = inputNomorHP();
                String iDAnggota = generateId(nama, nomorHP);
                System.out.printf("ID Anda : %s%n", iDAnggota);
            }
            else if (pilihan.equals("2")) {
                String iDAnggota = generateId(inputNama(), inputNomorHP());
                String tanggalTerima = inputTanggalTerima();
                String paketCuci = inputPaketCuci();
                int beratCucian = inputBeratCucian();
                generateNota(iDAnggota, paketCuci, beratCucian, tanggalTerima);
            }
            else {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        // TODO: Implement generate ID sesuai soal.
        String iDSementara = nama + "-" + nomorHP;

        int checkSum = 0;
        for (int i = 0; i < iDSementara.length(); i++) {
            if (Character.isLetter(iDSementara.charAt(i))) {
                int nilaiHuruf = nama.charAt(i) - 'A'+1;
                checkSum += nilaiHuruf;
            }
            else if (Character.isDigit(iDSementara.charAt(i))) {
                String nomorHPString = Character.toString(iDSementara.charAt(i));
                int nilaiNomorHP = Integer.parseInt(nomorHPString);
                checkSum += nilaiNomorHP;
            }
            else {
                checkSum += 7;
            }
        }
        String checkSumString = Integer.toString(checkSum);
        if (checkSumString.length() > 2) {
            String checkSumFinal = checkSumString.substring(checkSumString.length()-2, checkSumString.length());
            checkSumString = checkSumFinal;
        }
        else if (checkSumString.length() == 1) {
            checkSumString = "0" + Integer.toString(checkSum);
        }
        String iDAnggota = iDSementara + "-" + checkSumString;
        return iDAnggota;
    }

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // TODO: Implement generate nota sesuai soal.
        int biaya = 0;
        int durasi = 0;
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

        int biayaTotal = biaya * berat;

        System.out.println("Nota Laundry");
        System.out.println("ID    : " + id);
        System.out.println("Paket : " + paket);
        System.out.println("Harga :");
        System.out.printf("%d kg x %d = %d%n", berat, biaya, biayaTotal);
        System.out.println("Tanggal Terima  : " + tanggalTerima);

        return null;
    }

    public static String conversionNama(String nama) {
        nama = nama.toUpperCase();
            if (nama.contains(" ")){
                String[] namaSplited = nama.split("\\s");
                nama = namaSplited[0];
            }
        return nama;
    }

    public static String inputNama() {
        System.out.println("Masukkan nama Anda: ");
        String nama = input.nextLine();
        nama = conversionNama(nama);
        return nama;
    }
    
    public static String inputNomorHP() {
        System.out.println("Masukkan nomor handphone Anda: ");
        String nomorHP = " ";
        boolean checkNomorHP = true;
        while (checkNomorHP) {
            if (input.hasNextDouble()) {
                nomorHP = input.nextLine();
                checkNomorHP = false;
            }
            else {
                System.out.println("Nomor hp hanya menerima digit");
                nomorHP = input.nextLine();
            }
        }
        return nomorHP;
    }

    public static String inputTanggalTerima() {
        SimpleDateFormat dateInput = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Masukkan tanggal terima: ");
        String tanggalTerima = input.nextLine();
        
        try {
            Date date = dateInput.parse(tanggalTerima);
            System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(date));
        }
        catch (ParseException e) {

        }
        return tanggalTerima;
    }

    public static String inputPaketCuci() {
        String paketCuci = "";
        boolean checkPaketCuci = true;
        while (checkPaketCuci) {
            System.out.println("Masukkan paket laundry: ");
            paketCuci = input.nextLine();
            String paketCuciCheck = paketCuci.toLowerCase();

            if (paketCuciCheck.equals("express") || paketCuciCheck.equals("fast")|| paketCuciCheck.equals("reguler") ) {
                checkPaketCuci = false;
            }
            else if (paketCuciCheck.equals("?")) {
                showPaket();
            }
            else {
                System.out.printf("Paket %s tidak diketahui%n[ketik ? untuk mencari tahu jenis paket]%n", paketCuci);
            }
        }
        return paketCuci;
    }

    public static int inputBeratCucian() {
        System.out.println("Masukkan berat cucian Anda [Kg]: ");
        int beratCucian = 0;
        boolean checkBeratCucian = true;
        while (checkBeratCucian) {
            try {
                beratCucian = input.nextInt();
                if (beratCucian > 0) {
                    if (beratCucian < 2) {
                        beratCucian = 2;
                        System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                    }
                    checkBeratCucian = false;
                }
                else {
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                }
            }
            catch (InputMismatchException exception) {
                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                input.next();
            }
        }
        input.nextLine();
        return beratCucian;
    }
}
