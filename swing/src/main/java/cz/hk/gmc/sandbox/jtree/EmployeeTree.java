package cz.hk.gmc.sandbox.jtree;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

/**
 * @since 27. 11. 2015
 */
public class EmployeeTree {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Book Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Employee javaBooks[] = { new Employee("A", "F", 9.99f), new Employee("B", "E", 4.99f),
                new Employee("C", "D", 9.95f) };
        Employee netBooks[] = { new Employee("AA", "CC", 9.99f), new Employee("BB", "DD", 9.99f) };
        Vector<Employee> javaVector = new TreeNodeVector<Employee>("A", javaBooks);
        Vector<Employee> netVector = new TreeNodeVector<Employee>("As", netBooks);
        Object rootNodes[] = { javaVector, netVector };
        Vector<Object> rootVector = new TreeNodeVector<Object>("Root", rootNodes);
        JTree tree = new JTree(rootVector);
        TreeCellRenderer renderer = new EmployeeCellRenderer();
        tree.setCellRenderer(renderer);
        JScrollPane scrollPane = new JScrollPane(tree);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
