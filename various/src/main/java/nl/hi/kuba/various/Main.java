package nl.hi.kuba.various;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Main {
    private static final Logger logger = LoggerFactory.getLogger(ArraysPlayground.class);

    static {
        //System.out.println("static call");
    }

    private static void cycle() {
        for (int x = 10; x > 5; x++) {
            if (x == 16) {
                x -= 11;
            }
            System.out.print(x + " ");
        }
        System.err.println("MYTODO: ");

        int[] a = {1, 2, 3, 4};
        for (int j : a) {
            if (j == 2) {
                continue;
            }
            for (int x = 0; x < 3; System.out.print(x)) {
                x++;
            }
        }
    }

    private static void logging() {
        final Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        logger.error("hello: {}", set);
        logger.error("hello: " + set);
    }

    private static void java8() {
        class Record {
            private Object[] array;

            Record(final int size) {
                this.array = new Object[size];

                for (int i = 0; i < size; i++) {
                    array[i] = new Integer(i);
                }
            }

            Object[] getArray() {
                return this.array;
            }
        }

        final int size = 1000000;
        final Record record = new Record(size);
        final Object[] array = new Object[size];

        final long t0 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            array[i] = record.getArray()[i];
        }
        final long t1 = System.currentTimeMillis();

        System.err.println("MYTODO: length= " + array.length);
        System.err.println("MYTODO: t0 = " + t0);
        System.err.println("MYTODO: t1 = " + t1);
        System.err.println("MYTODO: t1-t0 = " + (t1 - t0));


        final long t2 = System.currentTimeMillis();
        final Object[] array2 = Arrays.copyOf(record.getArray(), size);
        final long t3 = System.currentTimeMillis();

        System.err.println("MYTODO: length= " + array2.length);
        System.err.println("MYTODO: t2 = " + t2);
        System.err.println("MYTODO: t3 = " + t3);
        System.err.println("MYTODO: t3-t2 = " + (t3 - t2));
    }

    private static void replaceSeparators() {
        String path = "/home/user/some\\path\\to\\file";
        System.err.println("MYTODO: " + path);
        path = path.replaceAll("/", File.separator);
        System.err.println("MYTODO: " + path);
        path = path.replace("\\", File.separator);
        System.err.println("MYTODO: " + path);
    }

    private static void substr() {
        final String string = "abcd";
        System.err.println("MYTODO: " + string.substring(-1));
    }

    private static void printMultiple(String s, Object... args) {
        System.out.println(s);

        for (Object a : args) {
            System.out.println(a.toString());
        }
    }

    private static void testJoin() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        System.out.println(join(null));
        System.out.println(join(list));
        list.clear();
        System.out.println(join(list));
    }

    private static String join(List<String> categories) {
        if (categories == null || categories.size() <= 0) {
            return "";
        }

        final StringBuilder builder = new StringBuilder();

        for (String category : categories) {
            builder.append(category).append(",");
        }

        final String csvList = builder.toString();

        return csvList.substring(0, csvList.length() - 1); // remove last comma 
    }

    private static void unicode() {
        String[] characters = {"\u0009", // horizontal tab
                /*"\u000a", // new line*/
                /* "\u000d", // carriage return*/};

        for (String s : characters) {
            System.out.println(s);
        }

        /*
        "\u0020", // space
        "\ud7ff", // ?
        */
        char c = '\u0020';
        while (c != '\ud7ff') {
            System.out.println(c);
            c++;
        }

        /*
        "\ue000", // penguin
        "\ufffd", // replacement
        */
        c = '\ue000';
        while (c != '\ufffd') {
            System.out.println(c);
            c++;
        }
    }

    private static void fileStream() {
        InputStream inStream = null;

        try {
            String propertiesFilePathProperty = System.getProperty("PROPERTIES_FILE_PATH");
            String propertiesFilePath = System.getenv("PROPERTIES_FILE_PATH");
            File propertiesFile = new File(propertiesFilePathProperty);

            if (propertiesFilePath != null && propertiesFile.exists()) {
                inStream = new FileInputStream(propertiesFilePath);
            }
        } catch (Exception e) {
        }
    }

    private static void arrays() {
        String[] arr = new String[]{"hello"};

        if (arr != null && arr.length > 0) {
            System.out.println("l=" + arr.length);
        }
    }

    private static void syntax() {
        int a = 0b0001;
        int b = 0b0011;
        System.out.printf("%d op %d= %d\n", a, b, (a ^ b));
    }

    private static void maven() {
        String path = "/home/kuba/tmp/file.txt";
        File f = new File(path);

        if (f.isFile()) {
            long totalSpace = f.getTotalSpace();
            System.out.println("space=" + totalSpace);
        } else {
            System.out.println("file does not exist");
        }
    }

    private static void collections() {
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        Object[] array1 = list.toArray();
        String[] array2 = list.toArray(new String[list.size()]);
        put(array1);
        put(array2);
    }

    private static void properties() {
        Properties props = System.getProperties();

        for (String name : props.stringPropertyNames()) {
            System.out.println(name + "=" + System.getProperty(name));
        }
    }

    private static void entity() {
        Entity e = new Entity();
        e.doMath();
    }

    private static void operators() {
        int value = 5 % 3;
        System.out.printf("1 %%=" + value);
    }

    private static void log() {
        System.out.println("hello from main1");
        logger.warn("Hello this is a warning message");
        logger.info("Hello this is an info message");
        System.out.println("hello from main2");
    }

    private static void put(Object... objects) {
        for (Object o : objects) {
            if (o instanceof Collection) {
                Collection c = (Collection) o;
                Iterator it = c.iterator();

                while (it.hasNext()) {
                    System.out.println("DEBUG: " + it.next().toString());
                }
            } else {
                System.out.println("DEBUG: " + o.toString());
            }
        }
    }

    private static void dataTypes() {
        char[] chars = new char[3];
        chars[2] = 'a';
        chars[1] = 'b';
        System.err.println("MYTODO: " + chars.length);

        for (char x : chars) {
            System.err.println("MYTODO: " + x);
        }

        int[] ints = new int[3];
        for (int ix : ints) {
            System.err.println("MYTODO: " + ix);
        }

        byte b = 127;
        char c = 'z';
        int i = -1;
        long l = -1;
        float f = 1.0f;
        double d = 1.0;

        System.err.println("MYTODO: " + b);
        System.err.println("MYTODO: " + c);
        System.err.println("MYTODO: " + i);
        System.err.println("MYTODO: " + l);
        System.err.println("MYTODO: " + f);
        System.err.println("MYTODO: " + d);
    }

    private static void regex() {
        final String re = "(\\p{Ll})(\\p{Lu})";
        final String key = "hiBYE123";
        final String result = key.replaceAll(re, "$1 $2");
        System.err.println("MYTODO: " + result);
    }

    public static void main(String[] args) throws Exception {
        regex();
        //dataTypes();
        //arrays();
        //syntax();
        //maven();
        //collections();
        //properties();
        //entity();
        //operators();
        //log();
        //fileStream();
        //unicode();
        //testJoin();
        //printMultiple("format", new String[] { "one" }, "two");
        //substr();
        //replaceSeparators();
        //java8();
        //logging();
        //cycle();
    }
}
