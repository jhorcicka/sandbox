package nl.kuba.alertdialog;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class AlertDialog extends JDialog implements KeyListener {
    private final int WIDTH = 400;
    private final int HEIGHT = 200;

    @Override
    public void keyTyped(final KeyEvent e) {
        dispose();
    }

    @Override
    public void keyPressed(final KeyEvent e) {
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }

    public static void main(String[] args) {
        final String textToShow;
        
        if (args.length < 1) {
            textToShow = "Nothing to show...";
        } else {
            textToShow = args[0];
        }
        
        AlertDialog window = new AlertDialog("<html>" + textToShow + "</html>");
        window.setVisible(true);
    }
    
    public AlertDialog(String text) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addKeyListener(this);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - this.getWidth()) / 2 - WIDTH / 2;
        final int y = (screenSize.height - this.getHeight()) / 2 - HEIGHT / 2;
        setBounds(x, y, WIDTH, HEIGHT);
        
        final Dimension size = new Dimension(WIDTH, HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        
        final JLabel label = new JLabel(text);
        label.setVerticalAlignment(JLabel.TOP);
        add(label);
    }
}
