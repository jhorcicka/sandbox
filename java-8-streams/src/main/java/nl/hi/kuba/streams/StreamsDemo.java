package nl.hi.kuba.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamsDemo {
    public static void main(String[] args) {
        //sequential();
        //forEach();
        aggregateFunctions();
    }

    private static void aggregateFunctions() {
        final List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        System.out.println("distinct");
        numbers.stream().distinct().forEach(System.out::println);
        System.out.println("map");
        numbers.stream().map(i -> i * i).forEach(System.out::println);
        System.out.println("filter");
        numbers.stream().filter(i -> i > 3).forEach(System.out::println);
        System.out.println("limit");
        numbers.stream().limit(2).forEach(System.out::println);
        System.out.println("sorted ASC");
        numbers.stream().sorted().forEach(System.out::println);
        System.out.println("sorted DESC");
        numbers.stream().sorted((i1, i2) -> Integer.compare(i2, i1)).forEach(System.out::println);

        // limit
        // reduce
        // find
        // match
    }

    private static void collecting() {
        // collectors
    }

    private static void forEach() {
        final Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    private static void sequential() {
        final List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        final List<String> filtered = strings
                .stream()
                .filter(string -> !string.isEmpty())
                .collect(Collectors.toList());
        System.err.println(filtered);
    }

    private static void parallel() {
        final List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        final List<String> filtered = strings
                .parallelStream()
                .filter(string -> !string.isEmpty())
                .collect(Collectors.toList());
        System.err.println(filtered);
    }
}
