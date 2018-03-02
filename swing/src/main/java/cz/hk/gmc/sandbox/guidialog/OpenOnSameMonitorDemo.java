package cz.hk.gmc.sandbox.guidialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OpenOnSameMonitorDemo extends JFrame {
    public static void main(String[] args) {
        final OpenOnSameMonitorDemo demo = new OpenOnSameMonitorDemo();
        demo.run();
    }

    public void run() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension size = new Dimension(300, 180);
        setMinimumSize(size);
        setPreferredSize(size);

        setLayout(new BorderLayout());
        add(getHeader(), BorderLayout.NORTH);
        add(getMiddle(), BorderLayout.CENTER);
        add(getBottom(), BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    private JComponent getHeader() {
        final JLabel label = new JLabel("<html><h2>Parent window</h2></html>", JLabel.CENTER);
        return label;
    }

    private JComponent getMiddle() {
        return new JPanel();
    }

    private JComponent getBottom() {
        final JPanel panel = new JPanel();
        final JButton button = new JButton("Create next...");
        button.addActionListener(e -> {
            final OpenOnSameMonitorDemo demo = new OpenOnSameMonitorDemo();
            demo.run();
            showOnScreen(demo, this);
        });
        panel.add(button, BorderLayout.CENTER);

        return panel;
    }

    private static void showOnScreen(final Window frame, final Container parent) {
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice[] gd = ge.getScreenDevices();
        final GraphicsDevice frameDevice = parent.getGraphicsConfiguration().getDevice();

        for (final GraphicsDevice graphicsDevice : gd) {
            if (frameDevice.equals(graphicsDevice) || frameDevice.getIDstring().equals(graphicsDevice.getIDstring())) {
                final Rectangle monitorBounds = graphicsDevice.getDefaultConfiguration().getBounds();
                final int x = monitorBounds.x + (monitorBounds.width - frame.getWidth()) /2;
                final int y = monitorBounds.y + (monitorBounds.height - frame.getHeight()) / 2;
                frame.setLocation(x, y);
            }
        }
    }
}
