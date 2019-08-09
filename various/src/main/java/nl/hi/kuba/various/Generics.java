package nl.hi.kuba.various;

import java.util.ArrayList;
import java.util.List;

public class Generics {
    private interface A {
        void aMethod();
    }

    private interface B extends A {
        void bMethod();
    }

    private static List<A> getAList() {
        List<A> list = new ArrayList<>();
        list.add(() -> System.out.println("A-method"));

        return list;
    }

    private static List<B> getBList() {
        List<B> list = new ArrayList<>();
        list.add(new B() {
            @Override
            public void bMethod() {
                System.out.println("B-method");
            }

            @Override
            public void aMethod() {
                System.out.println("A-method");
            }
        });

        return list;
    }

    private static void runThem(List<? extends A> allItems) {
        for (A item : allItems) {
            item.aMethod();
        }
    }

    public static void main(String[] args) {
        runThem(getAList());
        runThem(getBList());
    }
}
