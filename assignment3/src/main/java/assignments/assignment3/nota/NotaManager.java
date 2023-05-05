package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];
    static public ArrayList<Nota> notaListTemp = new ArrayList<Nota>();

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        cal.add(Calendar.DATE, 1);
        System.out.println("Kamu tidur hari ini... zzz...");
        System.out.println("");
        for (Nota nota: notaList) {
            if(nota == null)
                continue;
            nota.toNextDay();
        }
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        notaListTemp.add(nota);
        notaList = notaListTemp.toArray(new Nota[notaListTemp.size()]);
        //System.out.println("Nota berhasil dibuat!");
    }
}
