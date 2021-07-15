package nl.hi.kuba.ocpjp;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // sortedSet();
        // interfaces();
        // stream();
        // datetime();
        //enumMap();
        //stringUtils();
        ocpjp8();
    }

    private static void ocpjp8() {
        String[] list = { "1", "2", "3" };
        //Arrays.parallelSetAll(list, x -> "x");
        //Arrays.parallelSetAll(list, x -> Integer.toString(x)+list[x]);
        Arrays.parallelSetAll(list, x -> x);
        System.err.println(list[0]);
        Arrays.stream(list).forEach(System.out::println);
    }

    private static void stringUtils() {
        final String input = "a+b+c+d";
        System.err.println("MYTODO: " + input.replaceAll("\\+", "-"));
        System.err.println("MYTODO: " + input.replace("+", "-"));
    }

    private enum MyBool {
        TRUE,
        FALSE
    }

    private static void enumMap() {
        final Map<MyBool, String> collect = Stream
                .of(MyBool.values())
                .collect(Collectors.toMap(value -> value, item -> stringRepresentation(item, false)));
        collect.keySet().forEach(System.out::println);
        collect.values().forEach(System.out::println);
    }

    private static String stringRepresentation(final MyBool value, final boolean uppercase) {
        return uppercase ? value.toString().toUpperCase() : value.toString().toLowerCase();
    }

    private static void datetime() {
        // Period p = Period.parse("P1Y2M").withMonths(3).withDays(15);
        // Period p = Period.parse("P1Y2M").withMonths(3);
        Period p = Period.parse("P1Y2M").withDays(15);
        // Period p = Period.parse("P1Y2M");
        System.err.println("MYTODO: " + p);
        LocalDate d = LocalDate.of(2018, 3, 15).minus(p);
        System.err.println("MYTODO: " + d);

    }

    private static void stream() {
        System.err.println("MYTODO: " + abc());
    }

    private static String abc() {
        Stream<String> stream = Stream.of("one", "two");
        return stream.filter(s -> s.length() > 10).findFirst().get();
    }

    private static void interfaces() {
        final List<String> list = Arrays.asList("java", "python");
        list.replaceAll(s -> s.toUpperCase());
        list.forEach(System.out::println);

        UnaryOperator<String> operator = s -> s.toUpperCase();
        Function<String, Integer> function = s -> {
            System.out.println(s.length());
            return s.length();
        };

        Stream.of("one", "three", "seven").forEach(function::apply);
    }

    private static void sortedSet() {
        System.err.println("MYTODO: " + ("z".compareTo("b")));
        Set<Person> set = new TreeSet<>();
        set.add(new Person("Liam", 30));
        set.add(new Person("Emma", 25));
        set.add(new Person("Emma", 20));
        for (Person person : set) {
            System.err.println("MYTODO: " + person.toString());
        }
    }
}
