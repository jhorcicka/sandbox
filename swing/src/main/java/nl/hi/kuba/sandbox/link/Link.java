package nl.hi.kuba.sandbox.link;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * @since 02. 10. 2015
 */
public class Link {
    private static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("ERROR: desktop is not supported. ");
        }
    }

    public static void main(String[] args) throws URISyntaxException {
        final URI uri = new URI("http://java.sun.com");
        class OpenUrlAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                open(uri);
            }
        }
        JFrame frame = new JFrame("Links");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(100, 400);
        Container container = frame.getContentPane();
        container.setLayout(new GridBagLayout());
        JButton button = new JButton();
        button.setText(
                "<HTML>Click the <FONT color=\"#000099\"><U>link</U></FONT>" + " to go to the Java website.</HTML>");
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setBackground(Color.WHITE);
        button.setToolTipText(uri.toString());
        button.addActionListener(new OpenUrlAction());
        container.add(button);
        frame.setVisible(true);
    }
}
