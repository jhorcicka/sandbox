package cz.hk.gmc.threads;

public class LoopInRun {
    private static class MyRunnable implements Runnable {
        private volatile boolean _running = false;
        private int _counter = 0;

        @Override
        public void run() {
            _running = true;

            while (_running && _counter < 10) {
                try {
                    System.out.println("run(): " + _counter++);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    _running = false;
                }
            }
        }

        public void end() {
            _running = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable r1 = new MyRunnable();
        Thread t1 = new Thread(r1);
        t1.start();

        // WAY_#1
        //t1.interrupt();

        // WAY_#2
        Thread.sleep(2000); // end can not be called before first run() is performed
        r1.end();
    }
}
