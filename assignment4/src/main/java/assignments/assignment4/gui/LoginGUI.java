package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;
import assignments.assignment3.user.menu.SystemCLI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
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
        idLabel = new JLabel("Masukkan ID Anda:");
        idTextField = new JTextField();
        idTextField.setColumns(73);

        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordField = new JPasswordField();
        passwordField.setColumns(73);

        loginButton = new JButton("Login");
        backButton = new JButton("Kembali");

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

        loginButton.addActionListener(e -> handleLogin());
        backButton.addActionListener(e -> handleBack());
        // TODO
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        idTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo("HomeGUI");
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        String idLogin = idTextField.getText();
        char[] password = passwordField.getPassword();
        String passwordLogin = new String(password);

        SystemCLI systemCLI = loginManager.getSystem(idLogin);
        if(systemCLI == null) {
            JOptionPane.showMessageDialog(this, "ID atau password invalid.", "Error", JOptionPane.ERROR_MESSAGE);
            idTextField.setText("");
            passwordField.setText("");
        }
        else {
            if (!MainFrame.getInstance().login(idLogin, passwordLogin)) {
                JOptionPane.showMessageDialog(this, "ID atau password invalid.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            idTextField.setText("");
            passwordField.setText("");
        }     
        // TODO
    }
}
