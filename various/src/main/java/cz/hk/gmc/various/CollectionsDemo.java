package cz.hk.gmc.various;

import java.util.ArrayList;
import java.util.List;

public class CollectionsDemo {
    public static void main(String[] args) {
        listToArray();
    }

    private static void listToArray() {
        final List<String> list = new ArrayList<>();
        list.add("str1");
        list.add("str2");
        list.add("str3");
        System.err.println("list-size: " + list.size());

        final String[] array = list.toArray(new String[0]);
        System.err.println("array-size: " + array.length);
    }
}
