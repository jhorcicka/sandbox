package cz.hk.gmc.various;

import java.util.ArrayList;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        runThem(getAList());
        runThem(getBList());
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
    
    private interface A {
        void aMethod();
    }
    
    private interface B extends A {
        void bMethod();
    }        
}
