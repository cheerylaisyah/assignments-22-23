package assignments.assignment3.nota;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import assignments.assignment1.NotaGenerator;

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services;
    private long baseHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggal) {
        this.id = totalNota;
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggal;
        this.sisaHariPengerjaan = NotaGenerator.toHariPaket(paket);
        totalNota++;
        //TODO
    }

    public void addService(LaundryService service){
        //TODO
    }

    public String kerjakan(){
        String temp = String.format("Nota %d : ", id);
        for(LaundryService objLaundryService : services){
            if (objLaundryService.isDone()) continue;
            temp += objLaundryService.doWork();
            if (objLaundryService == services[services.length-1]){
                isDone = true;
            }
        }
        // TODO
        return temp;
    }
    
    public void toNextDay() {
        sisaHariPengerjaan--;
        if(sisaHariPengerjaan <= 0){
            isDone = true;
        }
        // TODO
    }

    public long calculateHarga(){
        // TODO
        return -1;
    }

    public String getNotaStatus(){
        String message = this.isDone ? "Sudah selesai." : "Belum selesai.";
        return String.format("Nota %d : %s.%n", id, message);
        // TODO
        // return "";
    }

    @Override
    public String toString(){
        // TODO
        return "";
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }

    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }
}
