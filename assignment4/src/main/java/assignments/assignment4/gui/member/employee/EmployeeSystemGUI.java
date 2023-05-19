// import library dan class yang dibutuhkan
package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    // attributes
    public static final String KEY = "EMPLOYEE";

    // constructor
    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        return new JButton[]{
            new JButton("It's Nyuci Time"),
            new JButton("Display List Nota"),
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
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void displayNota() {
        String displayNota = "";
        // conditions untuk mengecek apakah ada nota yang terdaftar dalam system
        if (NotaManager.notaList.length > 0) {
            // for-loop untuk print nota status dari semua nota yang terdaftar dalam system
            for (Nota nota: NotaManager.notaList) {
                displayNota += nota.getNotaStatus() + "\n";
            }
            JOptionPane.showMessageDialog(this, displayNota, "List Nota", JOptionPane.INFORMATION_MESSAGE);
        }
        // jika belum ada nota yang terdaftar dalam system
        else {
            displayNota = "Belum ada nota :(";
            JOptionPane.showMessageDialog(this, displayNota, "List Nota", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void cuci() {
        JOptionPane.showMessageDialog(this, String.format("Stand back! %s beginning to nyuci!", loggedInMember.getNama()), "Nyuci Time", JOptionPane.INFORMATION_MESSAGE);
        String cuci = "";
        /// conditions untuk mengecek apakah ada nota yang terdaftar dalam system
        if (NotaManager.notaList.length > 0) {
            // for-loop untuk melakukan method kerjakan dari semua nota yang terdaftar dalam system
            for (Nota nota: NotaManager.notaList) {
                cuci += nota.kerjakan() + "\n";
            }
            JOptionPane.showMessageDialog(this, cuci, "Nyuci Results", JOptionPane.INFORMATION_MESSAGE);
        }
        // jika belum ada nota yang terdaftar dalam system
        else {
            JOptionPane.showMessageDialog(this, "Nothing to cuci here :(", "Nyuci Results", JOptionPane.ERROR_MESSAGE);
        }
    }
}
