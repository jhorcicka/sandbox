package nl.hi.kuba.ocpjp;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

import org.postgresql.ds.PGSimpleDataSource;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.JdbcRowSetImpl;

public class Main {
    enum TEST_CONSTANTS {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
    }
    static class A {
        int x;
        A(int y) {
            this.x = y;
        }
        public void print() {
            System.err.println("MYTODO: " + x);
        }
    }

    static class Search extends SimpleFileVisitor<Path> {
        public FileVisitResult visitFile(final Path path, BasicFileAttributes attributes) {
            final PathMatcher matcher = FileSystems.getDefault().getPathMatcher("regex:.*");
            if (matcher.matches(path.getFileName())) {
                System.err.println("MYTODO: " + path);
            }
            return FileVisitResult.CONTINUE;
        }
    }

    static int i = 10;
    public class Inner {
        public /*static*/ void print() {
            System.err.println("MYTODO: " );
        }
    }

    public enum Speed {
        FAST(2),
        FASTER(3),
        SLOW(1);
        private final int speed;
        Speed(final int speedCode) {
            this.speed = speedCode;
        }
    }

    interface In1 {
        public static void print() {
            System.err.println("MYTODO: ");
        }
    }

    public static void main(String[] args) throws Exception {
        //sortedMap();
        //sortedSet();
        //interfaces();
        //stream();
        //datetime();
        //enumMap();
        //stringUtils();

        // tests
        //t1(args);
        //t2();
        //attributes();
        //locales();
        //dates();
        //times();
        //testChange();
        //streams();
        //deque();
        //files();
        //enums();
        //predicates();
        //operators();
        //jdbc();
        //ocpjp8();
        //arrays();
        //optional();
        //new Main().methodX();
        //functions();
    }

    private static void functions() {
        final Function<Double, Double> f1 = d -> d * 2;
        final Function<Double, Integer> f2 = d -> d.intValue();
        final Function<Double, Integer> f3 = f2.compose(f1);
        System.err.println("MYTODO: " + new Double(12.6).intValue());
        System.err.println("MYTODO: " + f3.apply(12.6));
        final UnaryOperator operator = o -> null;
    }

    void methodX() throws Exception {
        for (int x = 0; x > 5; x++) {
            System.err.println("MYTODO: " + x);
        }
        OptionalInt opInt = OptionalInt.of(10);
    }

    private static void optional() {
        Optional<String> ops = Optional.of("1");
        final Optional<Integer> integer = ops.map(Integer::parseInt);
        System.err.println("MYTODO: " + integer);
    }

    private static void arrays() throws SQLException {
        final String[] list = {"1", "2", "3"};
        Arrays.parallelSetAll(list, x -> Integer.toString(x) + list[x]);
        System.err.println("MYTODO: " + list[0]);

    }

    private static void jdbc() throws SQLException {
        final Connection connection1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        System.err.println("MYTODO: " + connection1.getCatalog().isEmpty());

        final PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("postgres");
        ds.setUser("postgres");
        ds.setPassword("postgres");
        final Connection connection2 = ds.getConnection();
        System.err.println("MYTODO: " + connection2.getCatalog().isEmpty());

        //final Statement statement = connection1.createStatement();
        //final Statement statement = connection1.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY); // absolute() is not allowed
        final Statement statement = connection1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        //final Statement statement = connection1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        final ResultSet resultSet = statement.executeQuery("select * from employees");
        final JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet();
        final JdbcRowSet rowSet2 = new JdbcRowSetImpl(resultSet);
        resultSet.absolute(1);
        while (resultSet.next()) {
            System.err.println("MYTODO: " + resultSet.getString("firstname"));
        }

        final ResultSet resultSet1 = connection1.createStatement().executeQuery("select employeenumber from employees");
        final CachedRowSet cachedRowSet = new CachedRowSetImpl();
        cachedRowSet.populate(resultSet1);
        cachedRowSet.absolute(3);
        cachedRowSet.updateInt(1, 42);
        cachedRowSet.updateRow();
        connection1.setAutoCommit(false);
        connection1.commit();
        //cachedRowSet.acceptChanges(); // unable to get connection
        cachedRowSet.absolute(1);
        final ResultSet resultSet2 = connection1.createStatement().executeQuery("select employeenumber from employees");

        while (resultSet2.next()) {
            System.err.println("MYTODO: " + resultSet2.getInt("employeenumber"));
        }

        connection1.close();
        connection2.close();
    }

    private static void operators() {
        final BinaryOperator<Integer> bo1 = Integer::rotateLeft;
        final BinaryOperator<Integer> bo2 = (Integer i, Integer j) -> j += i;
        int x = 1;
        int y = 2;
        System.err.println("MYTODO: " + (y += x));
        System.err.println("MYTODO: " + (x = 4));

        final UnaryOperator<Double> up1 = d -> d + 2;
        final UnaryOperator<Double> up2 = d -> d + 3;
        final Function<Double, Double> up3 = up1.compose(up2);
        System.err.println("MYTODO: " + up3.apply(2.1));

    }

    private static void predicates() {
        final List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(10);
        list.add(11);
        list.add(5);
        final Predicate<Integer> p1 = i -> i > 5;
        final Predicate<Integer> p2 = p1.and(i -> i < 10);
        System.err.println("MYTODO: " + list.removeIf(p2));
    }

    private static void enums() {
        final TreeSet<TEST_CONSTANTS> set = new TreeSet<>();
        set.add(TEST_CONSTANTS.FIVE);
        set.add(TEST_CONSTANTS.FOUR);
        set.add(TEST_CONSTANTS.THREE);
        set.add(TEST_CONSTANTS.TWO);
        set.add(TEST_CONSTANTS.ONE);
        System.err.println("MYTODO: " + set);
    }

    private static void files() throws IOException {
        final Path pathX = Paths.get("users/whizlabs/output");
        System.err.println("MYTODO: " + pathX.toAbsolutePath());
        System.err.println("MYTODO: " + pathX.getNameCount());
        System.err.println("MYTODO: " + pathX.getName(2));
        System.err.println("MYTODO: " + pathX.getFileName());
        System.err.println("MYTODO: " + pathX.getRoot());

        final Path path1 = Paths.get("/tmp/one/two");
        final Path path2 = Paths.get("one/two/file.txt");
        System.err.println("MYTODO: 1->2=" + path1.resolve(path2));
        System.err.println("MYTODO: 2->1=" + path2.resolve(path1));

        final Path path3 = Paths.get("/tmp/one/two");
        final Path path4 = Paths.get("/tmp/one/two/three");
        System.err.println("MYTODO: 3->4=" + path3.relativize(path4));
        System.err.println("MYTODO: 4->3=" + path4.relativize(path3));

        if (Files.notExists(Paths.get("/tmp/one/two"))) {
            Files.createDirectory(Paths.get("/tmp/one"));
            Files.createDirectory(Paths.get("/tmp/one/two"));
        }
        try {
            Files.delete(Paths.get("/tmp/one"));
        } catch (final Exception e) {
            System.err.println("MYTODO: can't delete the directory. ");
            e.printStackTrace();
        }
        System.err.println("MYTODO: " + " deleted");


        final URL resource = Main.class.getClassLoader().getResource("test.txt");
        final String pathString = resource.getPath();
        final Path path = Paths.get(pathString);
        final List<String> strings = Files.readAllLines(path);
        strings.stream().skip(1).forEach(System.out::println);

        Map<String, Object> map = Files.readAttributes(path, "*");
        System.err.println("MYTODO: map=" + map);
        final BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
        final PosixFileAttributes posixFileAttributes = Files.readAttributes(path, PosixFileAttributes.class);
        final DosFileAttributes dosFileAttributes = Files.readAttributes(path, DosFileAttributes.class);
        System.err.println("MYTODO: " + basicFileAttributes.isDirectory());
        System.err.println("MYTODO: " + posixFileAttributes.permissions());
        System.err.println("MYTODO: " + dosFileAttributes.isReadOnly());
        Files.setAttribute(path, "dos:readonly", true);
        System.err.println("MYTODO: " + dosFileAttributes.isReadOnly());

        final FileVisitor<Path> searcher = new Search();
        Files.walkFileTree(path, searcher);

        System.err.println("MYTODO: " + Files.isSameFile(path, path));

        Files.list(Paths.get("/tmp")).forEach(System.out::println);

        Path path5 = Paths.get("");
        PosixFileAttributes posixFileAttributes1 = Files.readAttributes(path5, PosixFileAttributes.class);
        posixFileAttributes1.group().getName();
    }

    private static void deque() {
        ArrayDeque ad = new ArrayDeque();
        ad.add("a");
        ad.add("b");
        ad.add("c");
        ad.removeLastOccurrence("b");
        System.err.println("MYTODO: " + ad);
    }

    private static void streams() {
        final Stream flatMapStream = Stream.of(12.1, 12.5, 12.9).flatMap(d -> Stream.of(d.intValue()));
        flatMapStream.forEach(System.out::println);


        System.err.println("MYTODO: " + Stream.of("java", "exam", "oracle").map(s -> Optional.of("A")).collect(Collectors.toList()));
        System.err.println("MYTODO: " + Stream.of("java", "exam", "oracle").findFirst().map(s -> Optional.of("A")));
        System.err.println("MYTODO: " + Stream.of("java", "exam", "oracle").findFirst().flatMap(s -> Optional.of("A")));

        final Stream<String> stream2 = Stream.of("12", "13", "3", "1");
        final Optional<String> op = stream2.filter(s -> s.length() > 5).findFirst().flatMap(s -> Optional.of("4"));
        System.err.println("MYTODO: " + op);
        System.err.println("MYTODO: " + Optional.of(4).orElse(5));
        System.err.println("MYTODO: " + Optional.ofNullable(null).orElse(6));

        final Stream<String> stream1 = Stream.of("12", "13", "3", "1");
        double avg = stream1.collect(Collectors.averagingInt(s->Integer.parseInt(s)));
        System.err.println("MYTODO: " + avg);


        final IntStream ds = IntStream.of(1, 2, 2, 4);
        ds.forEach(x -> x +=x);
        //ds.forEach(x -> x * 2);
        final Stream<Integer> s1 = ds.boxed();
        System.err.println("MYTODO: " + s1.distinct().findFirst());

        final Stream<Double> s2 = Stream.of(2.2, 2.8, 2.5);
        final IntStream intStream = s2.mapToInt(Double::intValue);
        System.err.println("MYTODO: " + intStream.distinct().count());

        final Stream<String> s3 = Stream.of("10", "20");
        final boolean b = s3.anyMatch(s -> s.length() > 2);
        System.err.println("MYTODO: " + b);

        final Stream<Integer> s4 = Stream.of(2, 8, 5);
        final Optional<Integer> min = s4.filter(p -> p % 2 == 0).min(Integer::compareTo);
        System.err.println("MYTODO: " + min);
    }

    private static void testChange() {
        A a = new A(1);
        a.print();
        change(a);
        a.print();
    }

    private static void change(A a) {
        a = new A(2);
        a.print();
    }

    private static void times() {
        final LocalTime localTime = LocalTime.ofSecondOfDay(36000);
        System.err.println("MYTODO: " + localTime);
        //LocalTime.of
    }

    private static void dates() {
        ZonedDateTime zid = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Canada/Atlantic"));
        System.err.println("MYTODO: " + zid.getHour());

        Instant nowInstant = Instant.now();
        nowInstant = nowInstant.truncatedTo(ChronoUnit.SECONDS);
        System.err.println("MYTODO: " + nowInstant);

        final LocalDate ld10 = LocalDate.of(2015, 11, 25);
        final Year year = Year.of(2014);
        System.err.println("MYTODO: " + year.atDay(1));
        final Temporal temporal = ld10.adjustInto(year.atDay(1));
        System.err.println("MYTODO: " + temporal);

        final LocalDate ld0 = LocalDate.of(2015, 1, 31).plusDays(1);
        System.err.println("MYTODO: " + ld0.getMonth());
        System.err.println("MYTODO: " + ld0.getMonthValue());
        System.err.println("MYTODO: " + ld0);

        final LocalDate localDate1 = LocalDate.ofYearDay(2016, 366);
        System.err.println("MYTODO: " + localDate1);

        final LocalDate localDate2 = LocalDate.of(2015, 1, 31);
        final LocalDate now = localDate2.now();
        System.err.println("MYTODO: now=" + now);

        Duration duration = Duration.ofDays(-3);
        LocalDate localDate = LocalDate.of(2016, 1, 1);
        System.err.println("MYTODO: " + localDate.plus(duration.toDays(), ChronoUnit.DAYS));

        LocalDate ld1 = LocalDate.of(2012, 12, 31);
        System.err.println("MYTODO: " + ld1);
        LocalDate ld2 = LocalDate.ofYearDay(2012, 365);
        System.err.println("MYTODO: " + ld2);
        LocalDate ld3 = LocalDate.of(2013, 12, 31);
        System.err.println("MYTODO: " + ld3);
        LocalDate ld4 = LocalDate.ofYearDay(2013, 365);
        System.err.println("MYTODO: " + ld4);

        final Year y1 = Year.of(2000);
        final LocalDate localDate3 = y1.atMonthDay(MonthDay.of(11, 22));
        System.err.println("MYTODO: " + localDate3);

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y D");
        final LocalDate localDate4 = LocalDate.parse("2015 10", formatter);
        System.err.println("MYTODO: " + localDate4);

        final LocalDateTime localDateTime = LocalDateTime.of(2016, 10, 22, 11, 22);
        final LocalDate localDate5 = LocalDate
                .of(localDateTime.getYear(), localDateTime.getHour(), localDateTime.getDayOfWeek().getValue());
        System.err.println("MYTODO: " + localDate5);

        final String date = "1994-02-28 11:22";
        final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-M-dd H:m");
        final LocalDateTime localDateTime1 = LocalDateTime.parse(date, format);
        System.err.println("MYTODO: " + localDateTime1);

        final LocalDateTime localDateTime2 = LocalDateTime.of(2016, 12, 1, 12, 10);
        final String formattted = localDateTime2.format(DateTimeFormatter.BASIC_ISO_DATE);
        final LocalDateTime localDateTime3 = LocalDate.MAX.atStartOfDay();
        System.err.println("MYTODO: " + localDateTime3.getHour());

        Period p1 = Period.ofDays(2);
        System.err.println("MYTODO: " + p1.toString());
        p1 = p1.multipliedBy(30);
        System.err.println("MYTODO: " + p1.toString());
        System.err.println("MYTODO: " + p1.toTotalMonths());
    }

    class SRBundle_hi_IN extends ListResourceBundle {
        @Override
        protected Object[][] getContents() {
            Object[][] resources = new Object[1][2];
            resources[0][0] = "hello";
            resources[0][1] = "namaste";
            return resources;
        }
    }
    private static void locales() {
        final Locale locale = new Locale.Builder().setLanguage("cs").setRegion("CZ").build();
        System.err.println("MYTODO: " + locale.getDisplayLanguage());
        System.err.println("MYTODO: " + locale.getDisplayLanguage(new Locale("fr")));
        System.err.println("MYTODO: " + locale.getDisplayCountry());
        System.err.println("MYTODO: " + locale.getDisplayCountry(new Locale("FR")));

        Locale.setDefault(Locale.forLanguageTag("hi-IN"));
        System.err.println("MYTODO: " + Locale.getDefault().getCountry());
        System.err.println("MYTODO: " + Locale.getDefault().getLanguage());
        final ResourceBundle bundle = ResourceBundle.getBundle("SRBundle", Locale.getDefault());
        System.err.println("MYTODO: " + bundle.getString("hello"));

        /*
        Locale.FRENCH;
        Locale.FRANCE;
        DateFormat.LONG;
         */
        //ERROR new DateFormat(DateFormat.LONG, Locale.FRANCE);
    }

    private static void attributes() throws IOException {
        final URL resource = Main.class.getClassLoader().getResource("test.txt");
        final String pathString = resource.getPath();
        final Path path = Paths.get(pathString);
        final BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
        final UserPrincipal owner = Files.getOwner(path);
        System.err.println("MYTODO: " + owner.getName());
        final Map<String, Object> map = Files.readAttributes(path, "*");
        System.err.println("MYTODO: " + map.keySet());
    }

    private static void t2() {
        Stream<String> nameStream = Stream.of("Alice", "Bob", "Chuck");
        nameStream.peek(System.out::println).findAny();
        Integer.compareUnsigned(10, 20);

        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.add("a");
        queue.add("c");
        queue.add("b");
        queue.add("a");
        queue.removeLastOccurrence("a");
        System.err.println("MYTODO: " + queue);

        IntStream intStream = IntStream.of(1,2,2,4);
        Stream<String> stream = intStream.boxed().map(item -> item.toString());
        System.err.println("MYTODO: " + stream.distinct().findFirst());
    }

    private static void t1(String[] args) {
        System.err.println("MYTODO: " + args);
        assert args == null : "Null";
    }

    private static void ocpjp8() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
        dq.add(2);
        dq.add(3);
        System.err.println("MYTODO: " + dq.element());
        System.err.println("MYTODO: " + dq.poll());

        int z = 1;
        int x = 10;
        int y = 0;
        System.err.println(x++);
        y++;

        //List<? extends Number> list = new ArrayList<>();
        //list.add(new Integer(5)); // can not be assigned any value (Integer, Double, ...)
        //list.add(new Double(3.5));

        String[] list = { "1", "2", "3" };
        //Arrays.parallelSetAll(list, x -> "x");
        //Arrays.parallelSetAll(list, x -> Integer.toString(x)+list[x]);
        //Arrays.parallelSetAll(list, x -> x);
        System.err.println(list[0]);
        Arrays.stream(list).forEach(System.out::println);

        final Integer i1 = 10;
        System.err.println("MYTODO: " + i1.toString());
        System.err.println("MYTODO: " + Integer.toString(10, 2));

        IntSupplier intSupplier = Main::getRand10;
        System.err.println("MYTODO: " + intSupplier.getAsInt());

        final Map map = new HashMap<>();
        map.put(1, 1.0);
        map.put(2, 2.2);
        map.put(null, null);
        System.err.println("MYTODO: " + map);
        map.computeIfPresent(1, (BinaryOperator) (o1, o2) -> (int) o1 + (double) o2);
        System.err.println("MYTODO: " + map);
        map.computeIfPresent(2, (o1, o2) -> null);
        System.err.println("MYTODO: " + map);
        System.err.println("MYTODO: " + map.values());

        final Instant i2 = Instant.now();
        System.err.println("MYTODO: " + i2);
        System.err.println("MYTODO: " + i2.plus(2, ChronoUnit.HOURS));
        System.err.println("MYTODO: " + i2.getLong(ChronoField.INSTANT_SECONDS));

        final Duration d1 = Duration.ofDays(20);
        System.err.println("MYTODO: " + d1);

        final Period p1 = Period.ofDays(12).multipliedBy(3);
        System.err.println("MYTODO: " + p1.negated());

        final LocalTime lt1 = LocalTime.of(12, 59);
        System.err.println("MYTODO: " + lt1);

        final Map zid = ZoneId.SHORT_IDS;
        zid.put("Australia/Sydney", "AET");
        final ZoneId z2 = ZoneId.of("AET", zid);
        System.err.println("MYTODO: " + z2);

        Map map2 = new ConcurrentHashMap();
    }

    private static int getRand10() {
        return (int)(Math.random()*10) + 1;
    }

    private static void stringUtils() {
        final String input = "a+b+c+d";
        System.err.println("MYTODO: " + input.replaceAll("\\+", "-"));
        System.err.println("MYTODO: " + input.replace("+", "-"));

        Object o = new Object();
        o.hashCode();
        o.toString();
        o.equals(o);
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
        Period p1 = Period.parse("P1Y2M").withDays(15);
        // Period p = Period.parse("P1Y2M");
        System.err.println("MYTODO: " + p1);
        LocalDate d = LocalDate.of(2018, 3, 15).minus(p1);
        System.err.println("MYTODO: " + d);

        final Period p2 = Period.ofDays(2);
        System.err.println("MYTODO: " + p2);
        System.err.println("MYTODO: " + p2.withDays(3));
    }

    private static void stream() {
        class Student {
            private String name;
            public Student(final String name) {
                this.name = name;
            }
            public String getName() {
                return name;
            }
        }
        Stream<Student> students = Stream.of(new Student("A"), new Student("B"));
        students.map(s -> s.getName());
        students.flatMap(s -> Stream.of(new Object[] {}));

        List<Integer> intList = Arrays.asList(new Integer[] { 3, 4, 5, 6 });
        ConcurrentMap<Object, List<Integer>> map = intList.parallelStream()
                                                          .collect(Collectors.groupingByConcurrent(i -> i % 2 == 0 ? 1 : 2));
        IntStream ints = IntStream.of(1, 2, 2, 4);
        //Stream<String> intStream = ints.boxed().map(Integer::toString);
        Stream<String> intStream = ints.boxed().map(i -> i.toString());
        intStream.forEach(System.out::println);
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

        /*
        BiFunction<String, String, Double> biFunction = (s1, s2) -> Double.parseDouble(s1+s2);
        biFunction.apply("", "");
        Function<String, Double> fun = Double::parseDouble;
         */

        Stream.of("one", "three", "seven").forEach(function::apply);
    }

    private static void sortedMap() {
        TreeMap<String, String> map = new TreeMap();
        map.put("a", "apple");
        map.put("e", "egg");
        map.put("g", "gear");
        map.forEach((k, v) -> System.err.println("MYTODO: " + k + ":" + v));
        SortedMap<String, String> smap = map.subMap("a", "e");
        smap.put("b", "ball");
        smap.put("f", "fish");
        map.put("c", "cat");
        map.remove("a");
        System.err.println("MYTODO: " + smap);
        System.err.println("MYTODO: " + map);

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
