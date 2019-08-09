package nl.hi.kuba.sandbox.guidialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JFrame {
    public LoginDialog() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension size = new Dimension(300, 180);
        setMinimumSize(size);
        setPreferredSize(size);

        setLayout(new BorderLayout());
        add(getHeader(), BorderLayout.NORTH);
        add(getMiddle(), BorderLayout.CENTER);
        add(getBottom(), BorderLayout.SOUTH);
    }

    private JComponent getHeader() {
        final JLabel label = new JLabel("<html><h2>Login dialog</h2></html>", JLabel.CENTER);
        return label;
    }

    private JComponent getMiddle() {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Nick"));
        panel.add(new JTextField());
        panel.add(new JLabel("Password"));
        panel.add(new JPasswordField());

        return panel;
    }

    private JComponent getBottom() {
        final JPanel panel = new JPanel();
        panel.add(new JButton("Ok"), BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        LoginDialog dialog = new LoginDialog();
        dialog.pack();
        dialog.setVisible(true);
    }
}
