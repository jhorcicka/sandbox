package cz.hk.gmc.sandbox.guidialog;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @since 16. 11. 2015
 */
public class UndecoratedFrame extends JFrame implements MouseListener {
    public static void main(String[] args) {
        UndecoratedFrame frame = new UndecoratedFrame();
        frame.setVisible(true);
    }

    public UndecoratedFrame() {
        JLabel label = new JLabel("Undecorated frame", JLabel.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        int width = 200;
        int height = 50;
        final int x = (screenSize.width - this.getWidth()) / 2 - width / 2;
        final int y = (screenSize.height - this.getHeight()) / 2 - height / 2;
        setBounds(x, y, width, height);
        add(label);
        setUndecorated(true);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        setVisible(false);
        System.exit(0);
    }

    @Override
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }
}
