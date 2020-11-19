package nl.hi.kuba;

import java.util.List;

public class StreamsDemo {
    static boolean containsWithStream(final List<String> listA, final List<String> listB) {
        return listB.stream().anyMatch(listA::contains);
    }

    static boolean containsWithLoop(final List<String> listA, final List<String> listB) {
        for (final String string : listA) {
            if (listB.contains(string)) {
                return true;
            }
        }
        return false;
    }
}
