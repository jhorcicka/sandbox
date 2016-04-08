package cz.hk.gmc.sandbox;

import java.awt.*;
import javax.swing.*;

/**
 * @since 02. 10. 2015
 */
public class Layout {
    public static void main(String[] args) {
        //testGridBagLayout();
        testSimpleGridBagLayout();
    }

    private static void testSimpleGridBagLayout() {
        JFrame frame = new JFrame("frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        frame.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.gridx = 1;
        constraints1.gridy = 1;
        constraints1.fill = GridBagConstraints.BOTH;
        constraints1.anchor = GridBagConstraints.LINE_START;
        constraints1.weightx = 1;
        panel.add(new JTextField("text"), constraints1);

        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.gridx = 2;
        constraints2.gridy = 1;
        JButton button = new JButton("button");
        button.setPreferredSize(new Dimension(130, 25));
        panel.add(button, constraints2);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;

        frame.add(panel, constraints);
        frame.setVisible(true);
    }

    private static void testGridBagLayout() {
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

        frame.setVisible(true);
    }
}
