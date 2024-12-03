public class Mahasiswa {
    private int nim;
    private String nama;
    private String fakultas;

    public Mahasiswa(int nim, String nama, String fakultas) {
        this.nim = nim;
        this.nama = nama;
        this.fakultas = fakultas;
    }

    public int getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getFakultas() {
        return fakultas;
    }

    @Override
    public String toString() {
        return nim + "\t" + nama + "\t" + fakultas;
    }
}
