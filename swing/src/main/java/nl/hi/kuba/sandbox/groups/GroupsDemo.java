package nl.hi.kuba.sandbox.groups;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GroupsDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Group");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        JPanel topPanel = new JPanel(new GridLayout(0, 1));
        Border topBorder = BorderFactory.createTitledBorder("Top");
        topPanel.setBorder(topBorder);
        JPanel bottomPanel = new JPanel(new GridLayout(0, 1));
        Border bottomBorder = BorderFactory.createTitledBorder("Bottom");
        bottomPanel.setBorder(bottomBorder);
        panel.add(topPanel);
        panel.add(bottomPanel);

        topPanel.add(new JButton("one"));
        topPanel.add(new JButton("two"));
        bottomPanel.add(new JButton("three"));
        bottomPanel.add(new JButton("four"));

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
