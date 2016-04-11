package cz.hk.gmc.sandbox.izpack;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IzPackSlide extends JPanel {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        JFrame frame = new JFrame("IzPack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        frame.add(new IzPackSlide());
        frame.show();
    }

    public IzPackSlide() {
        super();
        setLayout(new GridBagLayout());

        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 1;
        titleConstraints.gridy = 1;
        titleConstraints.anchor = GridBagConstraints.LINE_START;
        titleConstraints.weightx = 1;
        add(new JLabel("ICON"), titleConstraints);

        GridBagConstraints textConstraints1 = new GridBagConstraints();
        textConstraints1.gridx = 1;
        textConstraints1.gridy = 2;
        textConstraints1.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("Please provide a software license file for using DataCleaner."), textConstraints1);

        GridBagConstraints textConstraints2 = new GridBagConstraints();
        textConstraints2.gridx = 1;
        textConstraints2.gridy = 3;
        textConstraints2.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("This file will have been provided to you by the DataCleaner team."), textConstraints2);

        GridBagConstraints comboConstraints = new GridBagConstraints();
        comboConstraints.gridx = 1;
        comboConstraints.gridy = 4;
        comboConstraints.anchor = GridBagConstraints.LINE_START;
        comboConstraints.fill = GridBagConstraints.HORIZONTAL;
        comboConstraints.gridwidth = GridBagConstraints.REMAINDER;
        comboConstraints.weightx = 1;
        add(new Combo(), comboConstraints);
    }
}
