package nl.hi.kuba.sandbox.link;

import java.io.IOException;
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.fit.cssbox.swingbox.BrowserPane;

/**
 * @since 31. 08. 2015
 */
public class SwingBox {
    private static void jEditorPane() {
        JEditorPane pane = new JEditorPane();

        pane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        pane.setEditable(false);
        pane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    System.out.println("URL=" + e.getURL());
                }
            }
        });

        pane.setText(
                "<html><p>hasdf;la kdf;ak jf;asdkf ja;sdkfj ;asldkfj ;asdlfk</p><a href=\"http://datacleaner" 
                        + ".org\">link</a><p>asd;fk asdf;k aj;dfk asj;dflk ajsd;f kasd;f kasd;flkasdf</p></html>");
        show(pane);
    }

    private static void swingbox() {
        BrowserPane swingbox = new BrowserPane();

        try {
            swingbox.setPage(new URL("http://datacleaner.org"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void show(JComponent component) {
        JFrame frame = new JFrame("Swingbox");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.getContentPane().add(component);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //swingbox();
        jEditorPane();
    }
}
