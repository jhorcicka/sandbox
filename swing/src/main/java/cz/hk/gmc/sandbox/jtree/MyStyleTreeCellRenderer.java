package cz.hk.gmc.sandbox.jtree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class MyStyleTreeCellRenderer implements TreeCellRenderer {
    private final Dimension size = new Dimension(100, 25);

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {
        final JLabel label = new JLabel("Nothing");
        label.setForeground(leaf ? Color.BLUE : Color.GREEN);
        final Object userObject = ((DefaultMutableTreeNode) value).getUserObject();

        if (userObject instanceof Employee) {
            final Employee employee = (Employee) userObject;
            label.setText(employee.firstName + " " + employee.lastName);
        }

        final JPanel panel = new JPanel();
        panel.add(label);
        panel.setBackground(leaf ? Color.GREEN : Color.BLUE);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setPreferredSize(size);
        return panel;
    }
}
