package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    boolean isDone = false;
    long harga;

    @Override
    public String doWork() {
        isDone = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public long getHarga(int berat) {
        harga = berat*1000;
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
