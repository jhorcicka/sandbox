package cz.hk.gmc.various;

class PrivateStaticDemo {
    private static final int COUNT = 999999999;
    public static void main(String[] args) {
        final PrivateStaticDemo demo = new PrivateStaticDemo();
        demo.run1();
        PrivateStaticDemo.run2();
    }
    
    private static void run2() {
        final long t0 = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < COUNT; i++) {
            sum += i;
        }
        System.err.println("MYTODO: sum=" + sum);
        final long t1 = System.currentTimeMillis();
        System.err.println("MYTODO: time=" + (t1 - t0));
    }

    private void run1() {
        final long t0 = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < COUNT; i++) {
            sum += i;
        }
        System.err.println("MYTODO: sum=" + sum);
        final long t1 = System.currentTimeMillis();
        System.err.println("MYTODO: time=" + (t1 - t0));
    }
}
