package assignments.assignment3.nota;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.CuciService;
import java.util.ArrayList;

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services;
    private ArrayList<LaundryService> servicesTemp = new ArrayList<LaundryService>();
    private long baseHarga;
    private int sisaHariPengerjaan;
    private int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    private int checkDone;
    private long hargaAkhir;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggal) {
        this.id = totalNota;
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggal;
        this.sisaHariPengerjaan = NotaGenerator.toHariPaket(paket);
        this.services = new LaundryService[0];
        this.servicesTemp = new ArrayList<LaundryService>();
        LaundryService newCuciService = new CuciService();
        addService(newCuciService);

        totalNota++;
    }

    public void addService(LaundryService service){
        servicesTemp.add(service);
        services = servicesTemp.toArray(new LaundryService[servicesTemp.size()]);
    }

    public String kerjakan(){
        String temp = String.format("Nota %d : ", id);
        for(LaundryService objLaundryService : services){
            if (objLaundryService.isDone()) {
                if (objLaundryService == services[services.length-1]) {
                    temp += "Sudah selesai.";
                    // isDone = true;
                }
                // temp += "Sudah selesai.";
                continue;
            }
            temp += objLaundryService.doWork();        
            if (objLaundryService == services[services.length-1]){
                isDone = true;
            }
            return temp;
        }
        return temp;
        
    }
    
    public void toNextDay() {
        sisaHariPengerjaan--;
        if(sisaHariPengerjaan < 0 && isDone != true){
            checkDone += 1;
            //isDone = true;
        }
    }

    public long calculateHarga(){
        baseHarga = getBerat() * NotaGenerator.toHargaPaket(getPaket());
        hargaAkhir = baseHarga;
        for (LaundryService objLaundryService : services) {
            hargaAkhir += objLaundryService.getHarga(berat);
        }
        hargaAkhir -= checkDone*2000;
        if (hargaAkhir < 0) return 0;
        
        return hargaAkhir;
    }

    public String getNotaStatus(){
        String message = this.isDone ? "Sudah selesai" : "Belum selesai";
        return String.format("Nota %d : %s.", id, message);
        
        // return "";
    }

    @Override
    public String toString(){
        return String.format("[ID Nota = %d]%n", id) + NotaGenerator.generateNota(member.getId(), paket, berat, tanggalMasuk, false) + "\n"
        + getServiceList();
    }

    public String getServiceList() {
        String message = "";
        for (LaundryService objLaundryService : services) {
            message += String.format("-%s @ Rp.%d%n", objLaundryService.getServiceName(), objLaundryService.getHarga(berat));
        }
        //cekk jamal
        if (checkDone == 0) {
            message += String.format("Harga Akhir: %d", calculateHarga());
        }
        else {
            message += String.format("Harga Akhir: %d Ada kompensasi keterlambatan %d * 2000 hari", calculateHarga(), checkDone);
        }
        return String.format("--- SERVICE LIST ---%n") + message;
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

    public Member getMember() {
        return member;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }
}
