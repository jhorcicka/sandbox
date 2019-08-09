package nl.hi.kuba.sandbox.layouts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class FillAvailableSpaceDemo extends JFrame {
    public FillAvailableSpaceDemo() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        final int width = 800;
        final int height = 600;
        final int space = 100;

        final Dimension size = new Dimension(width, height);
        setMinimumSize(size);
        setPreferredSize(size);

        final JLabel label = new JLabel("Progress");
        label.setBackground(Color.GREEN);
        label.setOpaque(true);
        label.setPreferredSize(new Dimension(width, space));

        final JLabel menu = new JLabel("MENU");
        menu.setBackground(Color.BLUE);
        menu.setOpaque(true);
        menu.setPreferredSize(new Dimension(space, width - space));

        final JTextArea textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(width - space, height - space));

        final Container pane = getContentPane();
        final BorderLayout layout = new BorderLayout();
        pane.setLayout(layout);
        pane.add(label, BorderLayout.NORTH);
        pane.add(menu, BorderLayout.WEST);
        pane.add(textArea, BorderLayout.CENTER);

        pack();
    }

    public static void main(String[] args) {
        new FillAvailableSpaceDemo().setVisible(true);
    }
}
