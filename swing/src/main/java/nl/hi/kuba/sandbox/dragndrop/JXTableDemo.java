package nl.hi.kuba.sandbox.dragndrop;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTaskPaneContainer;

public class JXTableDemo {
    private static void updateTableModel(final JXTable table, final int selectedRow, final int targetRow) {
        final String draggedValue = (String) table.getModel().getValueAt(selectedRow, 0);

        if (selectedRow < targetRow) { // go down
            for (int i = selectedRow; i < targetRow; i++) {
                final String movedValue = (String) table.getModel().getValueAt(i + 1, 0);
                table.getModel().setValueAt(movedValue, i, 0);
            }
        } else { // go up
            for (int i = selectedRow; i > targetRow; i--) {
                final String movedValue = (String) table.getModel().getValueAt(i - 1, 0);
                table.getModel().setValueAt(movedValue, i, 0);
            }
        }

        table.getModel().setValueAt(draggedValue, targetRow, 0);
        table.updateUI();
    }

    public static void main(String[] args) {
        final JXFrame frame = new JXFrame();
        final JXTaskPaneContainer taskPaneContainer = new JXTaskPaneContainer();
        final TableModel model = new TableModel() {
            private final String[] lines = new String[]{"one", "two", "three", "four", "five"};

            @Override
            public int getRowCount() {
                return lines.length;
            }

            @Override
            public int getColumnCount() {
                return 1;
            }

            @Override
            public String getColumnName(final int columnIndex) {
                return "column-" + (columnIndex + 1);
            }

            @Override
            public Class<?> getColumnClass(final int columnIndex) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(final int rowIndex, final int columnIndex) {
                return lines[rowIndex];
            }

            @Override
            public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
                lines[rowIndex] = (String) aValue;
            }

            @Override
            public void addTableModelListener(final TableModelListener l) {
            }

            @Override
            public void removeTableModelListener(final TableModelListener l) {
            }
        };

        final JXTable table = new JXTable();
        table.setModel(model);
        table.setDragEnabled(true);
        table.setDropMode(DropMode.ON);

        table.setDropTarget(new DropTarget(table, new DropTargetListener() {
            @Override
            public void dragEnter(final DropTargetDragEvent dtde) {
                System.out.println("enter");
            }

            @Override
            public void dragOver(final DropTargetDragEvent dtde) {
                System.out.println("over");
            }

            @Override
            public void dropActionChanged(final DropTargetDragEvent dtde) {
                System.out.println("changed");

            }

            @Override
            public void dragExit(final DropTargetEvent dte) {
                System.out.println("exit");

            }

            @Override
            public void drop(final DropTargetDropEvent dtde) {
                System.out.println("drop");
                final DropTarget source = (DropTarget) dtde.getSource();
                final JXTable table = (JXTable) source.getComponent();
                final int selectedRow = table.getSelectedRow();

                System.out.println("selected row: " + selectedRow);

                final Point point = dtde.getLocation();
                final int droppedAtRow = table.rowAtPoint(point);

                System.out.println("dropped at row: " + droppedAtRow);

                updateTableModel(table, selectedRow, droppedAtRow);
            }
        }));

        final JTextArea textArea = new JTextArea();
        final JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        taskPaneContainer.add(table);

        frame.add(taskPaneContainer, BorderLayout.EAST);
        frame.pack();
        frame.setVisible(true);
    }
}
