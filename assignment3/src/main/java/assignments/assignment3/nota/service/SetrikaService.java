package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    // set attributes awal
    boolean isDone = false;
    long harga;

    @Override
    /*
     * meng-override method doWork() pada class LaundryService.
     * akan mengembalikan String yang menandakan sedang menjalani service setrika
     */
    public String doWork() {
        isDone = true;
        return "Sedang menyetrika...";
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
     * akan mengembalikan total harga dari service setrika
     */
    public long getHarga(int berat) {
        harga = berat*1000;
        return harga;
    }

    @Override
    /*
     * meng-override method getServiceName() pada class LaundryService.
     * akan mengembalikan nama service, yaitu "Setrika"
     */
    public String getServiceName() {
        return "Setrika";
    }
}
