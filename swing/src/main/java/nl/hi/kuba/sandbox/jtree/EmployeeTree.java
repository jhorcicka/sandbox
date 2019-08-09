package nl.hi.kuba.sandbox.jtree;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.TreeCellRenderer;

public class EmployeeTree {
    private static JTree getTree() {
        final JTree tree = new JTree(getRootVector());
        final TreeCellRenderer renderer = new DCTreeCellRenderer();
        //final TreeCellRenderer renderer = new MyStyleTreeCellRenderer();
        //final TreeCellRenderer renderer = new EmployeeCellRenderer();
        tree.setCellRenderer(renderer);
        tree.setShowsRootHandles(false);

        final BasicTreeUI ui = (BasicTreeUI) tree.getUI();
        ui.setLeftChildIndent(0);
        ui.setRightChildIndent(0);

        return tree;
    }

    private static Vector<Object> getRootVector() {
        final Employee[] men = {new Employee("John", "Doe", 30000f), new Employee("Gregory", "Smith", 25000f),
                new Employee("Bob", "Black", 20000f)};
        final Employee[] women = {new Employee("Alice", "Wonderland", 23000f), new Employee("Jane", "Doe", 15000f)};
        final Vector<Employee> menVector = new TreeNodeVector<>("Men", men);
        final Vector<Employee> womenVector = new TreeNodeVector<>("Women", women);
        final Object[] rootNodes = {menVector, womenVector};
        final Vector<Object> rootVector = new TreeNodeVector<>("Root", rootNodes);

        return rootVector;
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("People tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTree tree = getTree();
        final JScrollPane scrollPane = new JScrollPane(tree);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
