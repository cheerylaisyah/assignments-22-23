package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;

public class NotaManager {
    // Attributes
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];
    static public ArrayList<Nota> notaListTemp = new ArrayList<Nota>();

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        cal.add(Calendar.DATE, 1);                                      // menambahkan satu hari ke tanggal sistem       
        System.out.println("Kamu tidur hari ini... zzz...");
        System.out.println("");
        // for-loop untuk memanggil method toNextDay() pada setiap nota di notaList
        for (Nota nota: notaList) {
            // jika nota kosong maka tidak akan memanggil method toNextDay()
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
    }
}
