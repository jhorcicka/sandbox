package nl.hi.kuba.sandbox.combo;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.WindowConstants;

public class GroupCombo extends JComboBox {
    private static class ExtendedComboBoxModel extends DefaultComboBoxModel {
        @Override
        public void setSelectedItem(Object anObject) {
            if (!(anObject instanceof Delimiter)) {
                super.setSelectedItem(anObject);
            } else {
                int index = getIndexOf(anObject);
                if (index < getSize()) {
                    setSelectedItem(getElementAt(index + 1));
                }
            }
        }
    }

    private static class ExtendedListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            if (!(value instanceof Delimiter)) {
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            } else {
                JLabel label = new JLabel(value.toString());
                Font f = label.getFont();
                label.setFont(f.deriveFont(f.getStyle() | Font.BOLD | Font.ITALIC));
                return label;
            }
        }
    }

    private static class Delimiter {
        private String text;

        private Delimiter(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public GroupCombo() {
        setModel(new ExtendedComboBoxModel());
        setRenderer(new ExtendedListCellRenderer());
    }

    public void addDelimiter(String text) {
        this.addItem(new Delimiter(text));
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        GroupCombo combo = new GroupCombo();
        combo.addDelimiter("Numbers");
        combo.addItem("one");
        combo.addItem("two");
        combo.addItem("three");
        combo.addDelimiter("Animals");
        combo.addItem("dog");
        combo.addItem("cat");
        combo.addItem("bat");

        frame.getContentPane().add(combo);
        frame.pack();
        frame.setVisible(true);
    }
}
