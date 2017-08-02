package cz.hk.gmc.sandbox.jtree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class DCTreeCellRenderer implements TreeCellRenderer {
    private final Dimension size = new Dimension(200, 30);

    private static final Color COLLAPSED_NODE_BG = new Color(174, 174, 174, 204); // #aeaeae 20 opacity
    private static final Color EXPANDED_NODE_BG = new Color(209, 76, 104); // #d14c68
    private static final Color LEAF_BG = new Color(255, 255, 255); // #ffffff
    private static final Color SELECTED_LEAF_BG = COLLAPSED_NODE_BG;

    private static final Color COLLAPSED_NODE_FG = new Color(65, 65, 65); // #414141
    private static final Color EXPANDED_NODE_FG = new Color(255, 255, 255); // #ffffff
    private static final Color LEAF_FG = COLLAPSED_NODE_FG;
    private static final Color SELECTED_LEAF_FG = COLLAPSED_NODE_FG;


    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {
        final ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
        final JLabel label = new JLabel("", rightArrow, JLabel.LEFT);
        label.setPreferredSize(size);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setVerticalAlignment(JLabel.TOP);
        label.setForeground(getForeground(leaf, selected, expanded));
        final Object userObject = ((DefaultMutableTreeNode) value).getUserObject();

        if (userObject instanceof Employee) {
            final Employee employee = (Employee) userObject;
            //final String text = leaf ? employee.getName() : "<html><b>" + employee.getName() + "</b></html>";
            label.setText("<html>" + employee.getName() + "</html>");
            final ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            label.setIcon(downArrow);
        } else {
            label.setText("<html><strong>" + value.toString() + "</strong></html>");
            //label.setText(value.toString());
        }

        final JPanel panel = new JPanel();
        panel.add(label);
        panel.setBackground(getBackground(leaf, selected, expanded));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setPreferredSize(size);
        return panel;
    }

    private Color getForeground(final boolean leaf, final boolean selected, final boolean expanded) {
        return getColorByStatus(leaf, selected, expanded, SELECTED_LEAF_FG, LEAF_FG, EXPANDED_NODE_FG,
                COLLAPSED_NODE_FG);
    }

    private Color getBackground(final boolean leaf, final boolean selected, final boolean expanded) {
        return getColorByStatus(leaf, selected, expanded, SELECTED_LEAF_BG, LEAF_BG, EXPANDED_NODE_BG,
                COLLAPSED_NODE_BG);
    }

    private Color getColorByStatus(final boolean leaf, final boolean selected, final boolean expanded,
            final Color selectedColor, final Color unselectedColor, final Color expandedColor,
            final Color collapsedColor) {
        if (leaf) {
            if (selected) {
                return selectedColor;
            } else {
                return unselectedColor;
            }
        } else {
            if (expanded) {
                return expandedColor;
            } else {
                return collapsedColor;
            }
        }
    }
}
