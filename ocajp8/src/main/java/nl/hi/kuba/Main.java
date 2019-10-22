package nl.hi.kuba;

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

    }

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

    public static void main(String... args) {
        timeClasses();
        /*
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
