// import class dan library yang dibutuhkan
package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {
    // attribute
    public static final String KEY = "MEMBER";

    // constructor
    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        return new JButton[]{
            new JButton("Saya ingin laundry"),
            new JButton("Lihat detail nota saya"),
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void showDetailNota() {
        // pembuatan scroll bar
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(500, 250));
        
        // pembuatan text area
        JTextArea textDetailNota = new JTextArea();
        textDetailNota.setEditable(false);
        
        // conditions untuk mengecek apakah ada nota yang terdaftar
        if (getLoggedInMember().getNotaList().length > 0){
            String detailNota = "";
            // for-loop untuk print detail nota dari semua nota yang terdaftar pada member
            for (Nota nota: getLoggedInMember().getNotaList()) {
                detailNota += nota.toString() + "\n";
            }
            textDetailNota.setText(detailNota);
            // jika nota > 1, akan menampilkan scroll bar
            if (getLoggedInMember().getNotaList().length > 1) {
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            }
        }
        // jika belum ada nota yang terdaftar
        else {
            textDetailNota.setText("Belum pernah laundry di CuciCuci, hiks:(");
        }
        scrollPane.setViewportView(textDetailNota);
        JOptionPane.showMessageDialog(this, scrollPane, "Detail Nota", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void createNota() {
        MainFrame.getInstance().navigateTo("CreateNotaGUI");
    }

}
