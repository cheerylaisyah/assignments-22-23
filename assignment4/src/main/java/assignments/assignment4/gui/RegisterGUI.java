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

    public RegisterGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        nameLabel = new JLabel("Masukkan nama Anda:");
        nameTextField = new JTextField();
        nameTextField.setColumns(73);

        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        phoneTextField = new JTextField();
        phoneTextField.setColumns(73);

        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordField = new JPasswordField();
        passwordField.setColumns(73);

        registerButton = new JButton("Register");
        backButton = new JButton("Kembali");

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

        registerButton.addActionListener(e -> handleRegister());
        backButton.addActionListener(e -> handleBack());
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo("HomeGUI");
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        String name = nameTextField.getText();
        String nomorHP = phoneTextField.getText();
        char[] passwordChar = passwordField.getPassword();
        String password = new String(passwordChar);

        if (name.equals("") || nomorHP.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Field tidak boleh kosong", "Empty Field", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (!NotaGenerator.isNumeric(nomorHP)) {
            JOptionPane.showMessageDialog(this, "Nomor hp hanya menerima digit", "Invalid Phone Number", JOptionPane.INFORMATION_MESSAGE);
            phoneTextField.setText("");
        }
        else {
            Member registeredMember = loginManager.register(name, nomorHP, password);
            if(registeredMember == null){
                JOptionPane.showMessageDialog(this, String.format("User dengan nama %s dan nomor hp %s sudah ada!\n", name, nomorHP), "Registration Failed", JOptionPane.INFORMATION_MESSAGE);
                handleBack();
            }
            else{
                JOptionPane.showMessageDialog(this, String.format("Berhasil membuat user dengan ID %s!\n", registeredMember.getId()), "Registration Success", JOptionPane.INFORMATION_MESSAGE);
                handleBack();
            }
        }
        // TODO
    }
}
