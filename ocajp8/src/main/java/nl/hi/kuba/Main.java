package nl.hi.kuba;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Main {
    private static class Animal {
        public void eat() {
            System.err.println("MYTODO: Animal eats...");
        }
    }

    private static class Dog extends Animal {
        public void eat() {
            System.err.println("MYTODO: Dog eats...");
        }
    }

    private static void time() {
        LocalTime time = LocalTime.of(0, 1, 2);
        System.err.println("MYTODO: " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        time.withHour(3);
        System.err.println("MYTODO: " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        time.withHour(3).withMinute(5);
        System.err.println("MYTODO: " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.err.println("MYTODO: " + time.withHour(3).withMinute(5).withSecond(10)
                .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    private static void complement() {
        System.err.println(0 ^ 0);
        System.err.println(0 ^ 1);
        System.err.println(1 ^ 0);
        System.err.println(1 ^ 1);
    }

    private static void test(final short s) {
        System.err.println("MYTODO: short s=" + s);
    }

    private static void test(final int i) {
        System.err.println("MYTODO: int i=" + i);
    }

    private static void test(final long l) {
        System.err.println("MYTODO: long l=" + l);
    }

    private static void test(final byte b) {
        System.err.println("MYTODO: byte b=" + b);
    }

    static int x = 10;

    private static void printArray(int[][] a) {
        System.err.println("MYTODO: array");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.err.println("MYTODO: " + a[i][j]);
            }
        }
    }

    private static void stringBuilderCapacity() {
        StringBuilder sb = new StringBuilder("ABCDEF");
        sb.delete(4, 6);
        sb.ensureCapacity(22);
        System.err.println("MYTODO: " + sb.capacity());
        sb.ensureCapacity(23);
        System.err.println("MYTODO: " + sb.capacity());
    }

    private static void polymorphism() {
        Animal a = new Dog();
        Dog d = new Dog();
        a.eat();
        d.eat();
    }

    private static void codePoint() {
        System.err.println("MYTODO: " + Character.getName(97));
        System.err.println("MYTODO: " + Character.codePointAt("hello", 2));
    }

    private static void sizes() {
        System.err.println("byte: " + Byte.SIZE);
        System.err.println("char: " + Character.SIZE);
        System.err.println("short: " + Short.SIZE);
        System.err.println("int: " + Integer.SIZE);
        System.err.println("long: " + Long.SIZE);
        System.err.println("float: " + Float.SIZE);
        System.err.println("double: " + Double.SIZE);
    }

    private static void timeClasses() {
        Period p = Period.parse("P2Y1M3D");
        Period p2 = Period.of(2, 1, 3);
        System.err.println("MYTODO: " + p);
        System.err.println("MYTODO: " + p2);
    }

    private static void array() {
        int[][] A = new int[2][3];
        A[1][0] = 1;
        A[1][1] = 2;
        A[1][2] = 3;
        printArray(A);

        int[][] B;
        B = new int[2][3];
        B[1][2] = 10;
        printArray(B);

        int[] ints = new int[3];
        String[] strings = new String[3];

        for (int i = 0; i < 3; i++) {
            System.err.println("MYTODO: " + ints[i]);
            System.err.println("MYTODO: " + strings[i]);
        }
    }

    private static void staticFor() {
        for (int x = 1; x < 3; x++) {
        }
        System.err.println("MYTODO: " + x);
    }

    /*
    private static int abc() {return 1;}
    private static double abc() {return 1.0;}
    */

    private static void arrays() {
        int[][] ints = new int[3][1];
        String[][] strings = {{"1", "2"}, {"-"}};
        System.err.println("MYTODO: " + strings[1]);
        System.err.println("MYTODO: " + strings[1][0]);
        System.err.println("MYTODO: " + strings[1][1]);
    }

    private static void subclasses() {
        SuperClass sc = new Sub();
        sc.method();

        char ch = 'c';
        int i = (int) ch;
        char c = (int) 'd';

        System.err.println("MYTODO: " + sc.pv);
        System.err.println("MYTODO: " + SuperClass.spv);
        System.err.println("MYTODO: " + (SuperClass.spv == sc.pv));
    }

    private static void date() {
        LocalDate ld = LocalDate.of(2000, 3, 1);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/M/yyyy");
        System.err.println("MYTODO: " + ld.format(fmt));
    }
    
    public static void main(String... args) {
        int out = 1 + 2 * 0 / 2;
        System.err.println("MYTODO: " + out);

        final String[] split = "Whizlabs".split("\\S");
        System.err.println("MYTODO: " + split.length);
        System.err.println(split);
        
        /*
        date();
        subclasses();
        arrays();
        staticFor();
        timeClasses();
        sizes();
        codePoint();
        polymorphism();
        stringBuilderCapacity();
        test((short)10);
        test(10);
        test(10L);
        test((byte)10);
        complement();
        System.err.println(String.format("%11d < %d", 1, 2, 3));
        time();
        array();
        */
    }
}
