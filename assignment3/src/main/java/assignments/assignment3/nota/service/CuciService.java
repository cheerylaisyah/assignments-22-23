package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    // set attribute awal
    boolean isDone = false;

    @Override
    /*
     * meng-override method doWork() pada class LaundryService.
     * akan mengembalikan String yang menandakan sedang menjalani service cuci
     */
    public String doWork() {
        isDone = true;
        return "Sedang mencuci...";
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
     * akan mengembalikan total harga dari service cuci
     */
    public long getHarga(int berat) {
        return 0;
    }

    @Override
    /*
     * meng-override method getServiceName() pada class LaundryService.
     * akan mengembalikan nama service, yaitu "Cuci"
     */
    public String getServiceName() {
        return "Cuci";
    }
}
