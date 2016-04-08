package cz.hk.gmc.sandbox;

import java.awt.*;

import javax.swing.*;

/**
 * @since 02. 10. 2015
 */
public class Layout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Links");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setLayout(layout);
        frame.add(panel);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setText(
                "This is a very long description that goes on multiple lines. " +
                        "This is a very long description that goes on multiple lines. " +
                        "This is a very long description that goes on multiple lines. " +
                        "This is a very long description that goes on multiple lines. " +
                        "This is a very long description that goes on multiple lines. "
        );
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(editorPane, constraints);

        constraints.gridwidth = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        JLabel label = new JLabel();
        label.setText("USERNAME");
        panel.add(label, constraints);

        Dimension size = new Dimension(100, 30);

        constraints.gridwidth = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;
        JTextField textField = new JTextField();
        textField.setMinimumSize(size);
        textField.setPreferredSize(size);
        textField.setMaximumSize(size);
        textField.setText("-1-");
        panel.add(textField, constraints);

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 100;
        panel.add(new JPanel(), constraints);

        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(new JButton("full width"), constraints);

        frame.setVisible(true);
    }
}
