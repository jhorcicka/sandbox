package nl.hi.kuba.sandbox.jtree;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

/**
 * @since 27. 11. 2015
 */
public class EmployeeCellRenderer implements TreeCellRenderer {
    JLabel firstNameLabel = new JLabel(" ");
    JLabel lastNameLabel = new JLabel(" ");
    JLabel salaryLabel = new JLabel(" ");
    JPanel renderer = new JPanel();
    DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
    Color backgroundSelectionColor;
    Color backgroundNonSelectionColor;

    public EmployeeCellRenderer() {
        firstNameLabel.setForeground(Color.BLUE);
        renderer.add(firstNameLabel);

        lastNameLabel.setForeground(Color.BLUE);
        renderer.add(lastNameLabel);

        salaryLabel.setHorizontalAlignment(JLabel.RIGHT);
        salaryLabel.setForeground(Color.RED);
        renderer.add(salaryLabel);
        renderer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        backgroundSelectionColor = defaultRenderer.getBackgroundSelectionColor();
        backgroundNonSelectionColor = defaultRenderer.getBackgroundNonSelectionColor();
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {
        Component returnValue = null;

        if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();

            if (userObject instanceof Employee) {
                Employee e = (Employee) userObject;
                firstNameLabel.setText(e.firstName);
                lastNameLabel.setText(e.lastName);
                salaryLabel.setText("" + e.salary);

                if (selected) {
                    renderer.setBackground(backgroundSelectionColor);
                } else {
                    renderer.setBackground(backgroundNonSelectionColor);
                }

                renderer.setEnabled(tree.isEnabled());
                returnValue = renderer;
            }
        }

        if (returnValue == null) {
            returnValue =
                    defaultRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        }

        return returnValue;
    }
}
