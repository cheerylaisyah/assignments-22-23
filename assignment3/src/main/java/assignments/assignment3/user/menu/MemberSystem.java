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
            String tanggalTerima = NotaManager.fmt.format(NotaManager.cal.getTime());
            String paket = getPaket(in);
            int berat = NotaGenerator.getBerat();
            Nota newNota = new Nota(loginMember, berat, paket, tanggalTerima);

            LaundryService newCuciService = new CuciService();
            newNota.addService(newCuciService);

            System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
            System.out.println("Hanya tambah 1000 / kg :0");
            System.out.print("[Ketik x untuk tidak mau]: ");
            String serviceSetrika = in.nextLine();
            // System.out.println("");
            LaundryService newServiceSetrika = new SetrikaService();
            if (serviceSetrika.equalsIgnoreCase("x")){
            }
            else {
                newNota.addService(newServiceSetrika);
            }

            System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!");
            System.out.println("Cuma 2000 / 4kg, kemudian 500 / kg");
            System.out.print("[Ketik x untuk tidak mau]: ");
            String serviceAntar = in.nextLine();
            // System.out.println("");
            LaundryService newServiceAntar = new AntarService();
            if (serviceAntar.equalsIgnoreCase("x")){
            }
            else {
                newNota.addService(newServiceAntar);
            }
            loginMember.addNota(newNota);
            NotaManager.addNota(newNota);
        }

        else if (choice == 2) {
            //System.out.println(loginMember.getNotaList());
            // System.out.println(loginMember.getId());
            // System.out.println(loginMember.getNotaList().length);
            // for (Member objMember : memberList) {
            //     System.out.println("tes");
            //     if (loginMember.getId().equals(objMember.getId())) {
            //         System.out.println(("jamal"));
                    for (Nota objNota: loginMember.getNotaList()) {
                        // System.out.println("astaghfirullah");
                        // System.out.println(loginMember.getId());
                        // System.out.println(objNota.getMember().getId());
                        if(loginMember.getId().equals(objNota.getMember().getId())) {
                            // System.out.println("wadaw");
                            System.out.println(objNota);
                        }
                    }
                // `}
            // }
        }

        else if (choice == 3) {
            boolean logout = true;
            return logout;
        }

        // TODO:
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
        // TODO
        memberListTemp.add(member);
        memberList = memberListTemp.toArray(new Member[memberListTemp.size()]);
    }

    public static String getPaket(Scanner in) {
        String paket = "";
        while (true) {
            System.out.println("Masukan paket laundry:");
            NotaGenerator.showPaket();
            paket = in.nextLine();

            if (paket.equals("?")) {
                NotaGenerator.showPaket();
                continue;
            }

            if (NotaGenerator.toHargaPaket(paket) < 0) {
                System.out.printf("Paket %s tidak diketahui\n", paket);
                // System.out.println("[ketik ? untuk mencari tahu jenis paket]");
            } else {
                break;
            }
        }
        return paket;
    }
}