package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    boolean isDone = false;

    @Override
    public String doWork() {
        isDone = true;
        // TODO
        return "Sedang mencuci...\n";
    }

    @Override
    public boolean isDone() {
        // TODO
        return isDone;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
