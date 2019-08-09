package nl.hi.kuba.various;

public class TryWithResources {

    private static class ABC implements AutoCloseable {
        @Override
        public void close() throws Exception {
            System.out.println("ABC.close() was called...");
        }

        public void run() {
            System.out.println("ABC.run() was called...");
        }
    }

    private static class XYZ implements AutoCloseable {
        @Override
        public void close() throws Exception {
            System.out.println("XYZ.close() was called...");
        }

        public void run() {
            System.out.println("XYZ.run() was called...");
        }
    }

    private static class DEF implements AutoCloseable {
        public void run() {
            System.out.println("DEF.run() was called...");
        }

        public void close(String something) {
            System.out.println("DEF.close(String) with " + something + " was called...");
        }

        public void close() {
            System.out.println("DEF.close() was called...");
        }
    }

    public static void trySpecialClose() {
        System.out.println("tryFinally: ");

        try (DEF def = new DEF()) {
            def.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tryFinally() {
        System.out.println("tryFinally: ");
        ABC abc = null;
        XYZ xyz = null;

        try {
            abc = new ABC();
            xyz = new XYZ();
            abc.run();
            xyz.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { // if both run() and close() throw exceptions, close()'s leaves the method
                if (abc != null) {
                    abc.close();
                }

                if (xyz != null) {
                    xyz.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void tryWithResources() {
        System.out.println("tryWithResources: ");

        try ( // if both run() and close() throw exceptions, run()'s leaves the method
              ABC abc = new ABC(); XYZ xyz = new XYZ()) {
            abc.run();
            xyz.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        tryFinally();
        tryWithResources();
        trySpecialClose();
    }
}
