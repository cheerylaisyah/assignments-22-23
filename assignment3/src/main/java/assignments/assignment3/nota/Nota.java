package assignments.assignment3.nota;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.CuciService;
import java.util.ArrayList;

public class Nota {
    // Attributes
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

    // Constructor
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
        addService(newCuciService);                                             // setiap nota otomatis akan memiliki service cuci
        totalNota++;                                                            // akan terus bertambah jika ada pembuatan nota baru
    }

    /*
     * Method untuk menambahkan obj Service ke Array Services
     */
    public void addService(LaundryService service){
        servicesTemp.add(service);
        services = servicesTemp.toArray(new LaundryService[servicesTemp.size()]);
    }

    /*
     * Method untuk mengerjakan service yang ada di Array Services setiap method ini dipanggil
     */
    public String kerjakan(){
        String temp = String.format("Nota %d : ", id);

        // for-loop untuk mengerjakan setiap service yang ada di Array Services
        for(LaundryService objLaundryService : services){
            // jika objLaundryService sudah selesai dikerjakan, maka akan lanjut ke ObjLaundryService selanjutnya
            if (objLaundryService.isDone()) {
                // jika objLaundryService sudah berada di akhir Array Services, maka akan mengembalikan "Sudah selesai."
                if (objLaundryService == services[services.length-1]) {
                    temp += "Sudah selesai.";
                }
                continue;
            }
            // akan memanggil method doWork() --> mengembalikan keterangan sedang mengerjakan service apa
            temp += objLaundryService.doWork();      
            // jika objLaundryService sudah berada di akhir Array Services, maka akan mengubah isDone Nota menjadi true  
            if (objLaundryService == services[services.length-1]){
                isDone = true;
            }
            return temp;
        }
        return temp;
        
    }
    
    /*
     * Method untuk mengurangi sisa hari pengerjaan setiap dipanggil
     */
    public void toNextDay() {
        sisaHariPengerjaan--;
        // jika sisa hari pengerjaan sudah melewati deadline, maka checkDone akan di-increament untuk diskon kompensasi keterlambatan
        if(sisaHariPengerjaan < 0 && isDone != true){
            checkDone += 1;
        }
    }

    /*
     * Method untuk mengkalkulasi harga pesanan Nota
     */
    public long calculateHarga(){
        baseHarga = getBerat() * NotaGenerator.toHargaPaket(getPaket());    // mengalikan berat dengan harga paket
        
        // menghitung harga akhir setelah ditambahkan dengan laundry service yang dipilih
        hargaAkhir = baseHarga;
        for (LaundryService objLaundryService : services) {
            hargaAkhir += objLaundryService.getHarga(berat);
        }
        hargaAkhir -= checkDone*2000;                                       // menghitung diskon kompensasi keterlambatan
        if (hargaAkhir < 0) return 0;                                       // jika hargaAkhir bernilai negatif maka akan return 0
        
        return hargaAkhir;
    }

    /*
     * Method untuk mendapatkan status pengerjaan dari nota
     */
    public String getNotaStatus(){
        String message = this.isDone ? "Sudah selesai" : "Belum selesai";
        return String.format("Nota %d : %s.", id, message);
    }

    @Override
    /*
     * Method override toString Nota sesuai dengan format
     */
    public String toString(){
        return String.format("[ID Nota = %d]%n", id) + NotaGenerator.generateNota(member.getId(), paket, berat, tanggalMasuk, false) + "\n"
        + getServiceList();
    }

    /*
     * Method untuk mendapatkan info tentang laundry services yang dipesan
     */
    public String getServiceList() {
        String message = "";
        // for-loop untuk mencetak setiap laundry service yang dipesan dan juga total harga untuk setiap servicenya
        for (LaundryService objLaundryService : services) {
            message += String.format("-%s @ Rp.%d%n", objLaundryService.getServiceName(), objLaundryService.getHarga(berat));
        }
        // jika pesanan selesai sebelum deadline, maka tidak ada diskon kompensasi keterlambatan
        if (checkDone == 0) {
            message += String.format("Harga Akhir: %d", calculateHarga());
        }
        // jika pesanan belum selesai saat deadline, maka akan ada diskon kompensasi keterlamatan
        else {
            message += String.format("Harga Akhir: %d Ada kompensasi keterlambatan %d * 2000 hari", calculateHarga(), checkDone);
        }
        return String.format("--- SERVICE LIST ---%n") + message;
    }

    // Method getter yang diperlukan

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
