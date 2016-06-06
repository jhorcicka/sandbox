package cz.hk.gmc.threads;

public class JoinSample implements Runnable {
    public void run() {
        Thread t = Thread.currentThread();
        System.out.print(t.getName());
        System.out.println(", in-status = " + t.isAlive());

        int size = 999;
        long sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                   sum += k;
                }
            }
        }
        System.out.println(sum);
    }

    public static void main(String args[]) throws Exception {
        Thread t = new Thread(new JoinSample());
        t.start();
        t.join();
        System.out.print(t.getName());
        System.out.println(", out-status = " + t.isAlive());
    }
}
