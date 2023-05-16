package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

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
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));  
        loginButton = new JButton("Login"); 
        registerButton = new JButton("Register");
        toNextDayButton = new JButton("Next Day");
        dateLabel = new JLabel(String.format("Hari ini: %s", NotaManager.fmt.format(NotaManager.cal.getTime())));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 30, 10);
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);
        gbc.insets = new Insets(0, 5, 60, 5);
        gbc.gridy = 1;
        mainPanel.add(loginButton, gbc);       
        gbc.insets = new Insets(30, 5, 60, 5);
        gbc.gridy = 2;
        mainPanel.add(registerButton, gbc);
        gbc.insets = new Insets(30, 5, 65, 5);
        gbc.gridy = 3;
        mainPanel.add(toNextDayButton, gbc);
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.gridy = 4;
        mainPanel.add(dateLabel, gbc);
       
        loginButton.addActionListener(e -> handleToLogin());
        registerButton.addActionListener(e -> handleToRegister());
        toNextDayButton.addActionListener(e -> handleNextDay());
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo("RegisterGUI"); //karena ga static harus getInstance
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo("LoginGUI");
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        NotaManager.toNextDay();
        dateLabel.setText((String.format("Hari ini: %s", NotaManager.fmt.format(NotaManager.cal.getTime()))));
        JOptionPane.showMessageDialog(null, "Kamu tidur hari ini...zzz...", "Next Day", JOptionPane.INFORMATION_MESSAGE);
    }
}
