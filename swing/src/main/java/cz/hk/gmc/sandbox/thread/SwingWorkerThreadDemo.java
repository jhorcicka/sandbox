package cz.hk.gmc.sandbox.thread;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

public class SwingWorkerThreadDemo {
    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        JButton button = new JButton("Answer!");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AnswerWorker(frame).execute();
            }
        });

        frame.getContentPane().add(button);
        frame.getContentPane().add(new JButton("Nothing"));
        frame.pack();
        frame.setVisible(true);
    }

    private static class AnswerWorker extends SwingWorker<Integer, Integer> {
        private final JFrame _frame;

        public AnswerWorker(JFrame frame) {
            _frame = frame;
        }

        protected Integer doInBackground() throws Exception {
            Thread.sleep(2000);
            return 42;
        }

        protected void done() {
            try {
                JOptionPane.showMessageDialog(_frame, get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
