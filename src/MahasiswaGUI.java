import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MahasiswaGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private MahasiswaList mahasiswaList;

    public static void main(String[] args) {
        new MahasiswaGUI();
    }

    public MahasiswaGUI() {
        mahasiswaList = new MahasiswaList();
        generateData();

        frame = new JFrame("Daftar Mahasiswa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        String[] columns = {"NIM", "Nama", "Fakultas"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Tambah");
        JButton deleteButton = new JButton("Hapus");
        JButton sortButton = new JButton("Urutkan");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(sortButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        loadTableData();
        addButton.addActionListener(e -> addMahasiswa());
        deleteButton.addActionListener(e -> deleteMahasiswa());
        sortButton.addActionListener(e -> sortMahasiswa());

        frame.setVisible(true);
    }

    private void loadTableData() {
        tableModel.setRowCount(0); 
        for (Mahasiswa mhs : mahasiswaList.getMahasiswaList()) {
            tableModel.addRow(new Object[]{mhs.getNim(), mhs.getNama(), mhs.getFakultas()});
        }
    }

    private void addMahasiswa() {
        JTextField nimField = new JTextField();
        JTextField namaField = new JTextField();
        JTextField prodiField = new JTextField();

        Object[] fields = {
            "NIM:", nimField,
            "Nama:", namaField,
            "Fakultas:", prodiField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Tambah Mahasiswa", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int nim = Integer.parseInt(nimField.getText());
                String nama = namaField.getText();
                String prodi = prodiField.getText();

                mahasiswaList.addMahasiswa(new Mahasiswa(nim, nama, prodi));
                loadTableData();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "NIM harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteMahasiswa() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            mahasiswaList.removeMahasiswa(selectedRow);
            loadTableData();
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih mahasiswa yang ingin dihapus!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void sortMahasiswa() {
        String[] methods = {"Bubble Sort", "Insertion Sort"};
        int methodChoice = JOptionPane.showOptionDialog(frame, "Pilih metode sorting:", "Metode Sorting",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, methods, methods[0]);

        if (methodChoice == -1) return; 

        String[] attributes = {"NIM", "Nama", "Fakultas"};
        int attributeChoice = JOptionPane.showOptionDialog(frame, "Urutkan berdasarkan:", "Sortir Mahasiswa",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, attributes, attributes[0]);

        if (attributeChoice == -1) return;

        if (methodChoice == 0) {
            mahasiswaList.bubbleSort(attributeChoice + 1);
        } else if (methodChoice == 1) {
            mahasiswaList.insertionSort(attributeChoice + 1);
        }

        loadTableData();
    }

    private void generateData() {
        mahasiswaList.addMahasiswa(new Mahasiswa(22009235, "Siti Nurhaliza", "Teknik"));
        mahasiswaList.addMahasiswa(new Mahasiswa(22009236, "Muhammad Fadli", "Kedokteran"));
        mahasiswaList.addMahasiswa(new Mahasiswa(22009237, "Rina Permata", "Hukum" ));
        mahasiswaList.addMahasiswa(new Mahasiswa(22009238, "Aditya Pratama", "FIB"));
        mahasiswaList.addMahasiswa(new Mahasiswa(22009240, "Hendra Gunawan", "FISIP"));
    }
}
