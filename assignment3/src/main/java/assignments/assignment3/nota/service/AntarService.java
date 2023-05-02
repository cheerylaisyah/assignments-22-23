package assignments.assignment3.nota.service;

// import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    boolean isDone = false;
    long harga;

    @Override
    public String doWork() {
        isDone = true;
        // TODO
        return "Sedang mengantar...\n";
    }

    @Override
    public boolean isDone() {
        // TODO
        return isDone;
    }

    @Override
    public long getHarga(int berat) {
        if (berat <= 2) {
            harga = 2000;
        }
        else {
            harga = 2000 + (berat*500);
        }
        // TODO
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
