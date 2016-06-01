package cz.hk.gmc.sandbox;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ProgressBar extends JFrame {
    public static void main(String[] args) {
        ProgressBar frame = new ProgressBar();
        frame.setVisible(true);
    }

    public ProgressBar() {
        URL imageUrl = getClass().getResource("/loading.gif");
        ImageIcon image = new ImageIcon(imageUrl);
        JLabel label = new JLabel("", image, JLabel.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        int iconWidth = 208;
        int iconHeight = 13;
        final int x = (screenSize.width - this.getWidth()) / 2 - iconWidth / 2;
        final int y = (screenSize.height - this.getHeight()) / 2 - iconHeight / 2;
        setBounds(x, y, iconWidth, iconHeight);

        add(label);
        setUndecorated(true);
    }
}
