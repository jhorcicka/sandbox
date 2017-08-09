package cz.hk.gmc.sandbox.border;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class BorderDemo extends JPanel {
    public BorderDemo() {
        final JLabel label2 = new JLabel("Label Using a Dashed Border");
        add(label2);

        final Border dashed = BorderFactory.createDashedBorder(Color.BLUE, 1, 1);
        final Border empty = new MatteBorder(0, 5, 0, 0, Color.BLACK);
        final Border compound = new CompoundBorder(empty, dashed);
        label2.setBorder(compound);
    }

    private static void createAndShowGUI() {
        final JFrame frame = new JFrame("Dashed Border");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BorderDemo());
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
