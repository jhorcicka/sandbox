package nl.hi.kuba.threads;

public class NoLoopInRun {
    private static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                longOperation();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void longOperation() {
            int size = 99999;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    for (int k = 0; k < size; k++) {
                        if (isInterrupted()) {
                            System.out.printf("INTERRUPTED!");
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        t1.start();

        System.out.println("before interrupt");
        t1.interrupt();
        System.out.println("after interrupt");
    }
}
