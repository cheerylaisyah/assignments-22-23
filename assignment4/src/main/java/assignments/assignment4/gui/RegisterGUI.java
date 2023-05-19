// import class dan library yang dibutuhkan
package assignments.assignment4.gui;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    // attributes
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    // constructor
    public RegisterGUI(LoginManager loginManager) {
        // set up layout
        super(new BorderLayout());
        this.loginManager = loginManager;

        // set up main panel
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));       // set padding panel
        mainPanel.setBackground(new Color(201, 228, 222));                          // set warna background panel

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // pembuatan label, textfield, dan button
        nameLabel = new JLabel("Masukkan nama Anda:");
        nameTextField = new JTextField(73);                                          

        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        phoneTextField = new JTextField(73);

        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordField = new JPasswordField(73);

        registerButton = new JButton("Register");
        registerButton.setForeground(Color.BLACK);                                  // set warna font button
        registerButton.setBackground(new Color(255, 255, 255));                     // set warna background button

        backButton = new JButton("Kembali");
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(new Color(255, 255, 255));

        // pengaturan posisi label, textfield, dan button, serta insert ke main panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(12,0,12,0);
        mainPanel.add(nameLabel, gbc);
        mainPanel.add(nameTextField, gbc);
        mainPanel.add(phoneLabel, gbc);
        mainPanel.add(phoneTextField, gbc);
        mainPanel.add(passwordLabel, gbc);
        mainPanel.add(passwordField, gbc);

        gbc.fill = GridBagConstraints.CENTER;
        mainPanel.add(registerButton, gbc);
        mainPanel.add(backButton, gbc);

        // jika button di klik, maka akan menjalankan method yang telah ditentukan
        registerButton.addActionListener(e -> handleRegister());
        backButton.addActionListener(e -> handleBack());
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // mengosongkan field
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");

        // menampilkan halaman HomeGUI
        MainFrame.getInstance().navigateTo("HomeGUI");
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // pengambilan input user
        String name = nameTextField.getText();
        String nomorHP = phoneTextField.getText();
        char[] passwordChar = passwordField.getPassword();
        String password = new String(passwordChar);

        // conditions untuk validasi semua field terisi
        if (name.equals("") || nomorHP.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Field tidak boleh kosong", "Empty Field", JOptionPane.ERROR_MESSAGE);
        }
        // conditions untuk validasi nomor HP adalah angka
        else if (!NotaGenerator.isNumeric(nomorHP)) {
            JOptionPane.showMessageDialog(this, "Nomor handphone harus berisi angka", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            phoneTextField.setText("");
        }
        else {
            // pembuatan object Member baru sesuai dengan input user
            Member registeredMember = loginManager.register(name, nomorHP, password);
            // conditions untuk validasi tidak ada duplikasi user
            if(registeredMember == null) {
                JOptionPane.showMessageDialog(this, String.format("User dengan nama %s dan nomor hp %s sudah ada!\n", name, nomorHP), "Registration Failed", JOptionPane.ERROR_MESSAGE);
                handleBack();
            }
            else {
                JTextArea confirmationRegistration = new JTextArea();
                confirmationRegistration.setEditable(false);
                confirmationRegistration.setBackground(new Color(238,238,238,255));
                confirmationRegistration.setText("Berhasil membuat user dengan ID " + registeredMember.getId());
                JOptionPane.showMessageDialog(this, confirmationRegistration, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
                handleBack();
            }
        }
    }
}
