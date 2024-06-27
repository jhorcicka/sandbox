package nl.hi.kuba.patterns.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuilderDemo {
    public static void main(String[] args) {
        //final ComplexClass complexObject = new ComplexClass(10, "hello", 3.14, 'x');
        //System.out.println(complexObject);

        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);

        /*
        for (int x : ints) {
            System.out.println(x);
        }

        Iterator<Integer> iterator = ints.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
         */

        iterate(ints.iterator()); // 1 2 3 4
        iterate(new Iterator() { // 0 - 9
            int counter = 0;
            @Override
            public boolean hasNext() {
                counter++;
                return counter < 10;
            }

            @Override
            public Object next() {
                return counter;
            }
        });


    }

    private static void iterate(Iterator it) {
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
