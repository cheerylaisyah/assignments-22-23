package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    boolean isDone = false;
    long harga;

    @Override
    public String doWork() {
        isDone = true;
        // TODO
        return "Sedang menyetrika...\n";
    }

    @Override
    public boolean isDone() {
        // TODO
        return isDone;
    }

    @Override
    public long getHarga(int berat) {
        harga = berat*1000;
        // TODO
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
