package assignments.assignment3.user;

import assignments.assignment3.nota.Nota;
import java.util.ArrayList;

public class Member {
    // Attributes
    protected String id;
    protected String password;
    protected String nama;
    protected Nota[] notaList = new Nota[0];
    protected ArrayList<Nota> notaListTemp = new ArrayList<Nota>();

    // Constructor
    public Member(String nama, String id, String password) {
        this.nama = nama;
        this.id = id;
        this.password = password;
    }

    /**
     * Method otentikasi member dengan ID dan password yang diberikan.
     *
     * @param id -> ID anggota yang akan diautentikasi.
     * @param password -> password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     */
    public boolean login(String id, String password) {
        return id.equals(this.id) && authenticate(password);
    }

    /**
     * Menambahkan nota baru ke NotaList instance member.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public void addNota(Nota nota) {
        notaListTemp.add(nota);
        notaList = notaListTemp.toArray(new Nota[notaListTemp.size()]);
        System.out.println("Nota berhasil dibuat!");
    }

    /**
     * Method otentikasi member dengan password yang diberikan.
     *
     * @param password -> sandi password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     */
    protected boolean authenticate(String password) {
        if(password.equals(this.password)){
            return true;
        }
        return false;
    }

    /*
     * Method getter yang diperlukan
     */

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public Nota[] getNotaList() {
        return notaList;
    }
}