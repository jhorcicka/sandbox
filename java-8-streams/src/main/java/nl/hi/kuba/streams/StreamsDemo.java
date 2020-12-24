package nl.hi.kuba.streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamsDemo {
    private static final List<Integer> NUMBERS = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

    public static void main(String[] args) {
        //sequential();
        //forEach();
        //aggregateFunctions();
        //collecting();
        //statistics();
    }

    private static void statistics() {
        final IntSummaryStatistics statistics = NUMBERS.stream().mapToInt((element) -> element).summaryStatistics();

        System.out.println("max=" + statistics.getMax());
        System.out.println("min=" + statistics.getMin());
        System.out.println("sum=" + statistics.getSum());
        System.out.println("average=" + statistics.getAverage());
    }

    private static void aggregateFunctions() {
        System.out.println("distinct");
        NUMBERS.stream().distinct().forEach(System.out::println);
        System.out.println("map");
        NUMBERS.stream().map(i -> i * i).forEach(System.out::println);
        System.out.println("filter");
        NUMBERS.stream().filter(i -> i > 3).forEach(System.out::println);
        System.out.println("limit");
        NUMBERS.stream().limit(2).forEach(System.out::println);
        System.out.println("sorted ASC");
        NUMBERS.stream().sorted().forEach(System.out::println);
        System.out.println("sorted DESC");
        NUMBERS.stream().sorted((i1, i2) -> Integer.compare(i2, i1)).forEach(System.out::println);
        System.out.println("reduce");
        final int total = NUMBERS.stream().reduce(0, (subtotal, element) -> subtotal + element);
        System.out.println("total=" + total);
        System.out.println("find");
        final int any = NUMBERS.stream().findAny().get();
        System.out.println("any=" + any);
        final int first = NUMBERS.stream().findFirst().get();
        System.out.println("first=" + first);
        final boolean anyMatch = NUMBERS.stream().anyMatch(element -> element > 2);
        System.out.println("anyMatch=" + anyMatch);
        final boolean allMatch = NUMBERS.stream().allMatch(element -> element > 2);
        System.out.println("allMatch=" + allMatch);
    }

    private static void collecting() {
        // list
        NUMBERS.stream().filter(element -> element > 2).collect(Collectors.toList()).forEach(System.out::println);
        // array
        final Integer[] biggerThan2 = NUMBERS.stream().filter(element -> element > 2).toArray(Integer[]::new);
        System.out.println("biggerThan2: ");
        Arrays.stream(biggerThan2).forEach(System.out::println);
        // joining
        final String joinedString = NUMBERS
                .stream()
                .filter(element -> element > 2)
                .map(element -> element.toString())
                .collect(Collectors.joining("x"));
        System.out.println("JoinedString=" + joinedString);
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
