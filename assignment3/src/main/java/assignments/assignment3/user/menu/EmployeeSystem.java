package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        switch (choice) {
            case 1 -> cuci();
            case 2 -> displayNota();
            case 3 -> logout = true;
            default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
        }
        return logout;
    }

    public static String displayNota() {
        String statusNota = "";
        for (Nota nota: notaList) {
            statusNota += String.format("%s\n", nota.getNotaStatus());
        }
        return statusNota;
    }

    public String cuciOpening() {
        return (String.format("Stand back! %s beginning to nyuci!\n", loginMember.getNama()));
    }

    public static String cuci() {
        String listNota = "";
        for (Nota nota: notaList) {
            listNota += String.format("%s\n", (nota.kerjakan()));
        }
        return listNota;
    }

    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
}
