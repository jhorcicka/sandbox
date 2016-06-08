package cz.hk.gmc.sandbox.progressbar;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class JProgressBarDemo extends JFrame {
    public static void main(String arg[]) {
        new JProgressBarDemo();
    }

    public JProgressBarDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        final JProgressBar bar = new JProgressBar();
        bar.setPreferredSize(new Dimension(200, 25));
        bar.setStringPainted(true);
        bar.setString("X/Y items completed...");
        bar.setIndeterminate(true);

        add(bar);
        pack();
        setVisible(true);
    }
}
