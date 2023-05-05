package assignments.assignment3.nota.service;

// import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    boolean isDone = false;
    long harga;

    @Override
    public String doWork() {
        isDone = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
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
    public String getServiceName() {
        return "Antar";
    }
}
