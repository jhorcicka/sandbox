package cz.hk.gmc.sandbox.jtree;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.TreeCellRenderer;

public class EmployeeTree {
    public static void main(String args[]) {
        final JFrame frame = new JFrame("Book Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTree tree = getTree();
        final JScrollPane scrollPane = new JScrollPane(tree);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private static JTree getTree() {
        final JTree tree = new JTree(getRootVector());
        final TreeCellRenderer renderer = new MyStyleTreeCellRenderer();
        //final TreeCellRenderer renderer = new EmployeeCellRenderer();
        tree.setCellRenderer(renderer);
        tree.setShowsRootHandles(false);
        final BasicTreeUI ui = (BasicTreeUI) tree.getUI();
        ui.setLeftChildIndent(0);
        ui.setRightChildIndent(0);

        return tree;
    }

    private static Vector<Object> getRootVector() {
        final Employee javaBooks[] = { new Employee("A", "F", 9.99f), new Employee("B", "E", 4.99f),
        new Employee("C", "D", 9.95f) };
        final Employee netBooks[] = { new Employee("AA", "CC", 9.99f), new Employee("BB", "DD", 9.99f) };
        final Vector<Employee> javaVector = new TreeNodeVector<>("A", javaBooks);
        final Vector<Employee> netVector = new TreeNodeVector<>("As", netBooks);
        final Object rootNodes[] = { javaVector, netVector };
        final Vector<Object> rootVector = new TreeNodeVector<>("Root", rootNodes);

        return rootVector;
    }
}
