package nl.hi.kuba.various;

public class Lambda {
    private interface MyRunnable {
        void run(String value);
    }

    private static void withArgs() {
        MyRunnable r1 = new MyRunnable() {
            @Override
            public void run(final String value) {
                System.out.println("r1: " + value);
            }
        };

        MyRunnable r2 = (value) -> System.out.println("r2: " + value);

        r1.run("42");
        r2.run("42");
    }

    private static void noArgs() {
        Runnable r1 = new Runnable() {
            public void run() {
                System.out.println("r1: run()");
            }
        };

        Runnable r2 = () -> System.out.println("r2: run()");

        r1.run();
        r2.run();
    }

    public static void main(String[] args) {
        noArgs();
        withArgs();
    }
}
