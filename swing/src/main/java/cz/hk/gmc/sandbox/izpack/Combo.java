package cz.hk.gmc.sandbox.izpack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Combo extends JPanel {

    public Combo() {
        super();

        setLayout(new GridBagLayout());

        JTextField textField= new JTextField();
        textField.setEditable(false);
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });

        JButton button = new JButton("Browse...");
        button .setSize(new Dimension(140, 25));
        button .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JPanel combo = new JPanel();
        combo.setLayout(new GridBagLayout());

        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 1;
        inputConstraints.gridy = 1;
        inputConstraints.fill = GridBagConstraints.HORIZONTAL;
        inputConstraints.anchor = GridBagConstraints.LINE_START;
        inputConstraints.weightx = 1;
        combo.add(textField, inputConstraints);

        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 2;
        buttonConstraints.gridy = 1;
        inputConstraints.fill = GridBagConstraints.HORIZONTAL;
        inputConstraints.anchor = GridBagConstraints.LINE_START;
        inputConstraints.weightx = 1;
        combo.add(button , buttonConstraints);

        GridBagConstraints comboConstraints = new GridBagConstraints();
        comboConstraints.fill = GridBagConstraints.HORIZONTAL;
        inputConstraints.anchor = GridBagConstraints.LINE_START;
        comboConstraints.weightx = 1;

        add(combo, comboConstraints);
        combo.setBackground(Color.RED);
    }
}
