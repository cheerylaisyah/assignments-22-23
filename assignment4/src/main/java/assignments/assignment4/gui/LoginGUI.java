// import class dan library yang dibutuhkan
package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;
import assignments.assignment3.user.menu.SystemCLI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    // attributes
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    // constructor
    public LoginGUI(LoginManager loginManager) {
        // setup layour
        super(new BorderLayout());
        this.loginManager = loginManager;

        // set up main panel
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));           // set padding panel
        mainPanel.setBackground(new Color(201, 228, 222));                              // set warna background panel

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
        idLabel = new JLabel("Masukkan ID Anda:");
        idTextField = new JTextField(73);

        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordField = new JPasswordField(73);

        loginButton = new JButton("Login");
        loginButton.setForeground(Color.BLACK);
        loginButton.setBackground(new Color(255, 255, 255));

        backButton = new JButton("Kembali");
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(new Color(255, 255, 255));

        // pengaturan posisi label, textfield, dan button, serta insert ke main panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20,0,20,0);
        mainPanel.add(idLabel, gbc);
        mainPanel.add(idTextField, gbc);
        mainPanel.add(passwordLabel, gbc);
        mainPanel.add(passwordField, gbc);

        gbc.fill = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);
        mainPanel.add(backButton, gbc);

        // jika button di klik, maka akan menjalankan method yang telah ditentukan
        loginButton.addActionListener(e -> handleLogin());
        backButton.addActionListener(e -> handleBack());
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // mengosongkan field
        idTextField.setText("");
        passwordField.setText("");

        // menampilkan halaman HomeGUI
        MainFrame.getInstance().navigateTo("HomeGUI");
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        // pengambilan input user
        String idLogin = idTextField.getText();
        char[] password = passwordField.getPassword();
        String passwordLogin = new String(password);

        SystemCLI systemCLI = loginManager.getSystem(idLogin);
        
        // mengecek apakah id terdapat dalam system
        if (systemCLI == null) {
            JOptionPane.showMessageDialog(this, "ID atau password invalid.", "Invalid ID or Password", JOptionPane.ERROR_MESSAGE);
            idTextField.setText("");
            passwordField.setText("");
        }
        else {
            // mengecek apakah id dan passowrd sesuai dengan data yang terdaftar
            if (!MainFrame.getInstance().login(idLogin, passwordLogin)) {
                JOptionPane.showMessageDialog(this, "ID atau password invalid.", "Invalid ID or Password", JOptionPane.ERROR_MESSAGE);
            }
            idTextField.setText("");
            passwordField.setText("");
        }     
    }
}
