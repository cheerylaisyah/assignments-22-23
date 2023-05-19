// import library dan class yang dibutuhkan
package assignments.assignment4.gui.member.member;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.RegisterGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    // attributes
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;
    private JPanel mainPanel;

    // constructor
    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;
        
        // set up main panel
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(80, 10, 10, 10));
        mainPanel.setBackground(new Color(201, 228, 222));
        
        initGUI();

        add(mainPanel, BorderLayout.CENTER);
        setBackground(new Color(201, 228, 222));
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // setting posisi object
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // pembuatan label, combobox, dan button, serta insert ke mainPanel sesuai posisi yang ditentukan
        paketLabel = new JLabel("Paket Laundry:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(paketLabel, gbc);

        paketComboBox = new JComboBox<>(new String[]{"Express", "Fast", "Reguler"});
        paketComboBox.setBackground(new Color(255, 255, 255));
        gbc.gridx = 1;
        mainPanel.add(paketComboBox, gbc);

        showPaketButton = new JButton("Show Paket");
        showPaketButton.setForeground(Color.BLACK);
        showPaketButton.setBackground(new Color(255, 255, 255));
        gbc.gridx = 2;
        mainPanel.add(showPaketButton, gbc);

        beratLabel = new JLabel("Berat Cucian (Kg):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(beratLabel, gbc);

        beratTextField = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(beratTextField, gbc);

        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / kg)");
        setrikaCheckBox.setBackground(new Color(201, 228, 222));
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(setrikaCheckBox, gbc);

        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4 kg pertama, kemudian 500 / kg)");
        antarCheckBox.setBackground(new Color(201, 228, 222));
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(antarCheckBox, gbc);

        createNotaButton = new JButton("Buat Nota");
        createNotaButton.setForeground(Color.BLACK);
        createNotaButton.setBackground(new Color(255, 255, 255));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        mainPanel.add(createNotaButton, gbc);

        backButton = new JButton("Kembali");
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(new Color(255, 255, 255));
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(backButton, gbc);

        // jika button diklik, akan menjalankan method yang ditentukan
        showPaketButton.addActionListener(e -> showPaket());
        createNotaButton.addActionListener(e -> createNota());
        backButton.addActionListener(e -> handleBack());
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // conditions untuk mengecek validasi berat --> harus berupa angka
        if (NotaGenerator.isNumeric(beratTextField.getText()) && Integer.parseInt(beratTextField.getText()) > 0) {
            // mengambil input user
            String pilihanPaket = (String) paketComboBox.getSelectedItem();
            int beratLaundry = Integer.parseInt(beratTextField.getText());

            // jika berat < 2, akan dihitung sebagai 2 kg
            if (beratLaundry < 2) {
                beratLaundry = 2;
                JOptionPane.showMessageDialog(this, "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

            // membuat object nota baru sesuai input user
            Nota nota = new Nota(memberSystemGUI.getLoggedInMember(), beratLaundry, pilihanPaket, fmt.format(cal.getTime()));

            // mengambil input user berdasarkan checkbox yang tersedia
            if (setrikaCheckBox.isSelected()) {
                nota.addService(new SetrikaService());
            }
            if (antarCheckBox.isSelected()) {
                nota.addService(new AntarService());
            }
            JOptionPane.showMessageDialog(this, "Nota berhasil dibuat!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // add nota baru ke list nota system dan list nota member tersebut
            NotaManager.addNota(nota);
            memberSystemGUI.getLoggedInMember().addNota(nota);

            // kembali ke default jika nota berhasil dibuat
            paketComboBox.setSelectedIndex(0);
            beratTextField.setText("");
            setrikaCheckBox.setSelected(false);
            antarCheckBox.setSelected(false);
        }
        // jika berat bukan berupa angka
        else {
            JOptionPane.showMessageDialog(this, "Berat cucian harus berisi angka positif!", "Error", JOptionPane.ERROR_MESSAGE);
            beratTextField.setText("");
        }
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // mengosongkan field
        paketComboBox.setSelectedIndex(0);
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);

        // menampilkan halaman MemberSystemGUI
        MainFrame.getInstance().navigateTo("MemberSystemGUI");
    }
}
