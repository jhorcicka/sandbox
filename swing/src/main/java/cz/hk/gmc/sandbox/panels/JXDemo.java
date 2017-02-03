package cz.hk.gmc.sandbox.panels;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.jdesktop.swingx.*;

public class JXDemo {
    public static void main(String[] args) {
        final JXFrame frame = new JXFrame();
        final JXTaskPaneContainer taskPaneContainer = new JXTaskPaneContainer();
        final JXTaskPane details = new JXTaskPane();
        details.setTitle("Details");

        final JTextArea textArea = new JTextArea();
        final JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        details.add(scroll);
        taskPaneContainer.add(details);

        frame.add(taskPaneContainer, BorderLayout.EAST);
        frame.pack();
        frame.setVisible(true);
    }
}
