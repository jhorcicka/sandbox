package nl.hi.kuba.sandbox.layouts;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BorderLayoutDemo extends JFrame {
    public BorderLayoutDemo() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        final Dimension size = new Dimension(300, 200);
        setMinimumSize(size);
        setPreferredSize(size);

        final JLabel label = new JLabel("text");
        final JButton button = new JButton("change");
        final Dimension buttonSize = new Dimension(200, 25);
        button.setMinimumSize(buttonSize);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);

        final Container pane = getContentPane();
        final BorderLayout layout = new BorderLayout();
        pane.setLayout(layout);
        pane.add(label, BorderLayout.WEST);
        pane.add(button, BorderLayout.EAST);
        pack();
    }

    public static void main(String[] args) {
        new BorderLayoutDemo().setVisible(true);
    }
}
