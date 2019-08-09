package nl.hi.kuba.sandbox;

/**
 * @since 31. 08. 2015
 */
public class Main {
    private static void testA() {
        Integer a = new Integer(127);
        Integer b = new Integer(127);
        Integer c = 127;
        Integer d = 127;
        Integer e = new Integer(200);
        Integer f = new Integer(200);
        Integer g = 200;
        Integer h = 200;
        System.out.println((a == b) + " " + (c == d) + " " + (e == f) + " " + (g == h));
    }

    public static void main(String[] args) {
        testA();
    }

}
