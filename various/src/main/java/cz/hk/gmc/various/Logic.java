package cz.hk.gmc.various;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logic {
    public static void main(String[] args) {
        //listToArrayTest();
        //listTest();
        //arrayTest();
    }

    private static void listToArrayTest() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        String[] array = new String[2];
        array = list.toArray(array);
        for (String s : array) {
            System.err.println("MYTODO: " + s);
        }
    }
    
    private static void listTest() {
        List<Integer> list = new ArrayList<>();
        System.err.println("MYTODO: " + list);
        list.add(1);
        System.err.println("MYTODO: " + list);
        list.add(3);
        System.err.println("MYTODO: " + list);
        list.add(4);
        System.err.println("MYTODO: " + list);
        list.add(2,3);
        System.err.println("MYTODO: " + list);
        
        list.removeIf(e -> e < 3);
        
        System.err.println("MYTODO: " + list);
    }
    
    private static void arrayTest() {
        int[] a1 = {4,2,1,3};
        for (int i : a1) {
            System.err.println("MYTODO: " + i);
        }
        //Arrays.sort(a1);
        Arrays.sort(a1, 1, 4);
        for (int i : a1) {
            System.err.println("MYTODO: " + i);
        }
        
        int[][] a2 = new int[2][];
        
        int[][][] array = {
                {
                        { 0 },
                        { 1 },
                        { 2 }
                    
                }, 
                {
                        { 3 },
                        { 4 },
                        { 5 }
                    
                }, 
        };
        
        System.err.println("MYTODO: " + array.length);
        System.err.println("MYTODO: " + array[0].length);
        System.err.println("MYTODO: " + array[0][0].length);
    }
}
