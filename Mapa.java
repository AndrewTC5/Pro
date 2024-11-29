package Pro;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;

public class Mapa extends JDialog {
    private Unidad unidad;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel ldatos;

    public Mapa() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        ldatos.setText(unidad.mostrarInfoLabel());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Mapa dialog = new Mapa();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
