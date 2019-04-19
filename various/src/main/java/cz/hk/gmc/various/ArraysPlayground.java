package cz.hk.gmc.various;

import java.util.ArrayList;
import java.util.List;

public class ArraysPlayground {
    public static void main(String[] args) {
        listToArray();
    }
    
    private static void listToArray() {
        final List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        
        final String[] array = list.toArray(new String[] {});
        System.err.println("MYTODO: " + array.length);
        
        for (final String item : array) {
            System.err.println("MYTODO: " + item);
        }
    }
}
