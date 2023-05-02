package assignments.assignment3.nota;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import assignments.assignment1.NotaGenerator;
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
        servicesTemp.add(service);
        services = servicesTemp.toArray(new LaundryService[servicesTemp.size()]);
        //TODO
    }

    public String kerjakan(){
        String temp = String.format("Nota %d : ", id);
        System.out.println(services.length);
        for(LaundryService objLaundryService : services){
            if (objLaundryService.isDone()) continue;
            temp += objLaundryService.doWork();        
            if (objLaundryService == services[services.length-1]){
                isDone = true;
            }
            return temp;
        }
        return temp;
        // TODO
        
    }
    
    public void toNextDay() {
        sisaHariPengerjaan--;
        if(sisaHariPengerjaan <= 0){
            isDone = true;
        }
        // TODO
    }

    public long calculateHarga(){
        baseHarga = getBerat() * NotaGenerator.toHargaPaket(getPaket());
        long tempHarga = baseHarga;
        for (LaundryService objLaundryService : services) {
            tempHarga += objLaundryService.getHarga(berat);
        }
        // TODO
        return tempHarga;
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
        return String.format("[ID Nota = %d]%n", id) + NotaGenerator.generateNota(member.getId(), paket, berat, tanggalMasuk, false) + "\n"
        + getServiceList();
    }

    public String getServiceList() {
        String message = "";
        for (LaundryService objLaundryService : services) {
            message += String.format("-%s @ Rp.%d%n", objLaundryService.getServiceName(), objLaundryService.getHarga(berat));
        }
        message += String.format("Harga Akhir: %d%n", calculateHarga());
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
