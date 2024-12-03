import java.util.LinkedList;

public class MahasiswaList {
    private LinkedList<Mahasiswa> mahasiswaList;

    public MahasiswaList() {
        mahasiswaList = new LinkedList<>();
    }

    public void addMahasiswa(Mahasiswa mahasiswa) {
        mahasiswaList.add(mahasiswa);
    }

    public void removeMahasiswa(int index) {
        if (index >= 0 && index < mahasiswaList.size()) {
            mahasiswaList.remove(index);
        }
    }

    public LinkedList<Mahasiswa> getMahasiswaList() {
        return mahasiswaList;
    }

    public void bubbleSort(int attribute) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < mahasiswaList.size() - 1; i++) {
                boolean condition = compare(mahasiswaList.get(i), mahasiswaList.get(i + 1), attribute);
                if (condition) {
                    Mahasiswa temp = mahasiswaList.get(i);
                    mahasiswaList.set(i, mahasiswaList.get(i + 1));
                    mahasiswaList.set(i + 1, temp);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public void insertionSort(int attribute) {
        for (int i = 1; i < mahasiswaList.size(); i++) {
            Mahasiswa key = mahasiswaList.get(i);
            int j = i - 1;

            while (j >= 0 && compare(mahasiswaList.get(j), key, attribute)) {
                mahasiswaList.set(j + 1, mahasiswaList.get(j));
                j--;
            }
            mahasiswaList.set(j + 1, key);
        }
    }

    private boolean compare(Mahasiswa m1, Mahasiswa m2, int attribute) {
        switch (attribute) {
            case 1:
                return m1.getNim() > m2.getNim();
            case 2:
                return m1.getNama().compareToIgnoreCase(m2.getNama()) > 0;
            case 3:
                return m1.getFakultas().compareToIgnoreCase(m2.getFakultas()) > 0;
            default:
                return false;
        }
    }
}
