package cz.hk.gmc.sandbox;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @since 16. 11. 2015
 */
public class UndecoratedFrame extends JFrame {
    public static void main(String[] args) {
        UndecoratedFrame frame = new UndecoratedFrame();
        frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public UndecoratedFrame() {
        ImageIcon image = new ImageIcon("/tmp/loading.gif");
        JLabel label = new JLabel("", image, JLabel.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        int iconWidth = 128;
        int iconHeight = 15;
        final int x = (screenSize.width - this.getWidth()) / 2 - iconWidth / 2;
        final int y = (screenSize.height - this.getHeight()) / 2 - iconHeight / 2;
        setBounds(x, y, iconWidth, iconHeight);

        add(label);
        setUndecorated(true);
    }
}
