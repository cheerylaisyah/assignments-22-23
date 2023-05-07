package assignments.assignment3.user.menu;
import java.util.Scanner;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        if (choice == 1){
            // attributes yang diperlukan
            String tanggalTerima = NotaManager.fmt.format(NotaManager.cal.getTime());
            String paket = getPaket(in);
            int berat = NotaGenerator.getBerat();
            Nota newNota = new Nota(loginMember, berat, paket, tanggalTerima);

            // meminta input apakah user ingin menambahkan service setrika
            System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
            System.out.println("Hanya tambah 1000 / kg :0");
            System.out.print("[Ketik x untuk tidak mau]: ");
            String serviceSetrika = in.nextLine();

            LaundryService newServiceSetrika = new SetrikaService();         

            // jika user meng-input "x" maka tidak akan melakukan apa-apa
            if (serviceSetrika.equalsIgnoreCase("x")){
            }
            // akan menambahkan service setrika ke nota user
            else {
                newNota.addService(newServiceSetrika);
            }

            // meminta input apakah user ingin menambahkan service antar
            System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!");
            System.out.println("Cuma 2000 / 4kg, kemudian 500 / kg");
            System.out.print("[Ketik x untuk tidak mau]: ");
            String serviceAntar = in.nextLine();

            LaundryService newServiceAntar = new AntarService();

            // jika user meng-input "x" maka tidak akan melakukan apa-apa
            if (serviceAntar.equalsIgnoreCase("x")){
            }
            // akan menambahkan service antar ke nota user
            else {
                newNota.addService(newServiceAntar);
            }

            // menambahkan nota baru ke notaList member dari notaList sistem
            loginMember.addNota(newNota);
            NotaManager.addNota(newNota);
        }

        else if (choice == 2) {
            // for-loop untuk mencetak semua nota dari user
            for (Nota objNota: loginMember.getNotaList()) {
                if (loginMember.getId().equals(objNota.getMember().getId())) {
                    if (objNota == loginMember.getNotaList()[loginMember.getNotaList().length-1]){
                        System.out.println(objNota);
                        continue;
                    }
                    System.out.println(objNota);
                    System.out.println("");
                }
            }
        }

        else if (choice == 3) {
            boolean logout = true;
            return logout;
        }

        else {
            System.out.println("Pilihan tidak valid, silakan coba lagi.");
        }

        return false;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) { 
        memberListTemp.add(member);
        memberList = memberListTemp.toArray(new Member[memberListTemp.size()]);
    }

    /*
     * Method untuk mendapatkan input paket apa yang dipilih user
     */
    public static String getPaket(Scanner in) {
        String paket = "";
        System.out.println("Masukan paket laundry:");
        NotaGenerator.showPaket();

        while (true) {
            paket = in.nextLine();

            if (paket.equals("?")) {
                NotaGenerator.showPaket();
                continue;
            }

            if (NotaGenerator.toHargaPaket(paket) < 0) {
                System.out.printf("Paket %s tidak diketahui\n", paket);
                System.out.println("[ketik ? untuk mencari tahu jenis paket]");
            } else {
                break;
            }
        }
        return paket;
    }
}