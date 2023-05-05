package nl.hi.kuba.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamsDemo {
    static class InputColumn {
        private final String name;

        InputColumn(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    static class Consumer {
        private final int count;

        Consumer(final int count) {
            this.count = count;
        }

        public InputColumn[] getOutputColumns() {
            final InputColumn[] columns = new InputColumn[count];
            for (int i = 0; i < count; i++) {
                columns[i] = new InputColumn("" + i);
            }
            return columns;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "Consumer[" + count + "]";
        }
    }

    private static final List<Integer> NUMBERS = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

    public static void main(String[] args) {
        //sequential();
        //forEach();
        //aggregateFunctions();
        //collecting();
        //statistics();
        reduce();
        //creation();
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
        final List<String> filtered =
                strings.parallelStream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.err.println(filtered);
    }

    private static void reduce() {
        final Consumer c1 = new Consumer(3);
        final Consumer c2 = new Consumer(2);
        final Consumer c3 = new Consumer(5);
        final List<Consumer> consumers = new ArrayList<>();
        consumers.add(c1);
        consumers.add(c2);
        consumers.add(c3);

        final List<InputColumn> columns = consumers
                .stream()
                .map(consumer -> Arrays.asList(consumer.getOutputColumns()))
                .reduce(new ArrayList<>(), (allColumns, consumerColumns) -> {
                    allColumns.addAll(consumerColumns);
                    return allColumns;
                });

        System.err.println(columns);

        final String listToString =
                consumers.stream().map(Consumer::toString).collect(Collectors.joining(", ", "[", "]"));
        System.err.println("listToString=" + listToString);

        final double columnCountAvg = consumers.stream().collect(Collectors.averagingInt(Consumer::getCount));
        System.err.println("columnCountAvg=" + columnCountAvg);

        final int columnCountSum = consumers.stream().collect(Collectors.summingInt(Consumer::getCount));
        System.err.println("columnCountSum=" + columnCountSum);

        final IntSummaryStatistics statistics =
                consumers.stream().collect(Collectors.summarizingInt(Consumer::getCount));
        System.err.println("statistics=" + statistics);

        final Map<Integer, List<Consumer>> collectorMapOfLists =
                consumers.stream().collect(Collectors.groupingBy(Consumer::getCount));
        System.err.println("collectorMapOfLists=" + collectorMapOfLists.toString());

        final Map<Boolean, List<Consumer>> mapPartioned =
                consumers.stream().collect(Collectors.partitioningBy(element -> element.getCount() > 3));
        System.err.println("mapPartitioned=" + mapPartioned.toString());

        final Collector<Consumer, ?, LinkedList<Consumer>> toLinkedList =
                Collector.of(LinkedList::new, LinkedList::add, (first, second) -> {
                    first.addAll(second);
                    return first;
                });
        final LinkedList<Consumer> linkedListOfConsumers = consumers.stream().collect(toLinkedList);
        System.err.println("linkedListOfConsumers=" + linkedListOfConsumers);
    }

    private static void creation() {
        final Stream<String> streamOfArray = Stream.of("A", "B", "C");
        printFirst(streamOfArray, "streamOfArray");

        final Stream<String> streamBuilder = Stream.<String> builder().add("a").add("b").add("c").build();
        printFirst(streamBuilder, "streamBuilder");

        final Stream<String> streamGenerated = Stream.generate(() -> "element").limit(3);
        printFirst(streamGenerated, "streamGenerated");

        final Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(5);
        printFirst(streamIterated, "streamIterated");

        final IntStream intStream = IntStream.range(1, 3);
        System.err.println("intStream[0]=" + intStream.findFirst().getAsInt());

        final LongStream longStream = LongStream.rangeClosed(1, 3);
        System.err.println("longStream[0]=" + longStream.findFirst().getAsLong());

        final IntStream streamOfChars = "abc".chars();
        System.err.println("streamOfChars[0]=" + streamOfChars.findFirst().getAsInt());

        final Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");
        printFirst(streamOfString, "streamOfString");

        // Java 8 streams can't be reused.

        final Stream<String> onceModifiedStream = Stream.of("abcd", "bbcd", "cbcd").skip(1);
        printFirst(onceModifiedStream, "onceModifiedStream");

        // Intermediate operations which reduce the size of the stream should be placed before operations
        // which are applying to each element.

        int reducedParallel = Arrays.asList(1, 2, 3).parallelStream().reduce(10, (a, b) -> a + b, (a, b) -> {
            System.out.println("combiner was called");
            return a + b;
        });
        System.out.println("reducedParallel=" + reducedParallel);
    }

    private static void printFirst(final Stream<?> stream, final String name) {
        System.err.println(name + "[0]=" + stream.findFirst().get());
    }
}
