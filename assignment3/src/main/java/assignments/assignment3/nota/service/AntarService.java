package assignments.assignment3.nota.service;

public class AntarService implements LaundryService{
    // set attributes awal
    boolean isDone = false;
    long harga;

    @Override
    /*
     * meng-override method doWork() pada class LaundryService.
     * akan mengembalikan String yang menandakan sedang menjalani service antar
     */
    public String doWork() {
        isDone = true;
        return "Sedang mengantar...";
    }

    @Override
    /*
     * meng-override method isDone() pada class LaundryService.
     * akan mengembalikan true jika method doWork() dikerjakan minimal sekali
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    /*
     * meng-override method getHarga(int berat) pada class LaundryService.
     * akan mengembalikan total harga dari service antar
     */
    public long getHarga(int berat) {
        if (berat <= 4) {
            harga = 2000;
        }
        else {
            harga = berat*500;
        }
        return harga;
    }

    @Override
    /*
     * meng-override method getServiceName() pada class LaundryService.
     * akan mengembalikan nama service, yaitu "Antar"
     */
    public String getServiceName() {
        return "Antar";
    }
}
