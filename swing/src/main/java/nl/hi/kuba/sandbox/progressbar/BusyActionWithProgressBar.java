package nl.hi.kuba.sandbox.progressbar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class BusyActionWithProgressBar {
    private static JFrame _frame = new JFrame("frame");
    private static JLabel _label = new JLabel("-");

    private static void busyOperation2() {
        long sum = 0;
        int size = 9999;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    sum += k * j * i;
                    _label.setText("Sum=" + sum);
                }
            }
        }
    }

    private static void busyOperation1() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void showMainWindow() {
        _frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        _frame.setSize(500, 400);
        _frame.add(_label);
        _frame.setVisible(true);
    }

    public static void main(String[] args) {
        showMainWindow();

        ProgressBar bar = new ProgressBar();
        bar.show();

        //busyOperation1();
        busyOperation2();

        bar.hide();
    }
}
