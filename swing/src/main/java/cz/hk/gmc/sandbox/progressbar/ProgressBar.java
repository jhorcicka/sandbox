package cz.hk.gmc.sandbox.progressbar;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class ProgressBar {
    private ProgressBarFrame _frame;
    private Thread _thread;

    public static void main(String[] args) {
        try {
            ProgressBar bar = new ProgressBar();
            bar.show();
            Thread.sleep(3000);
            bar.hide();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class ProgressBarFrame extends JFrame implements Runnable {
        private static final String PATH = "/loading.gif";
        private static final int WIDTH = 208;
        private static final int HEIGHT = 13;

        private ProgressBarFrame() {
            setUndecorated(true);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            final URL imageUrl = getClass().getResource(PATH);
            final ImageIcon image = new ImageIcon(imageUrl);
            final JLabel label = new JLabel("", image, JLabel.CENTER);
            add(label);

            final Toolkit toolkit = Toolkit.getDefaultToolkit();
            final Dimension screenSize = toolkit.getScreenSize();
            final int x = (screenSize.width - getWidth()) / 2 - WIDTH / 2;
            final int y = (screenSize.height - getHeight()) / 2 - HEIGHT / 2;
            setBounds(x, y, WIDTH, HEIGHT);
        }

        public void run() {
            setAlwaysOnTop(true);
            setVisible(true);
        }

        private void terminate() {
            setVisible(false);
            dispose();
        }
    }

    public void show() {
        _frame = new ProgressBarFrame();
        _thread = new Thread(_frame);
        _thread.start();
    }

    public void hide() {
        try {
            _frame.terminate();
            _thread.interrupt();
            _thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
