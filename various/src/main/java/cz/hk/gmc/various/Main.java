package cz.hk.gmc.various;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.*;

class Main {
    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    static {
        //System.out.println("static call");
    }

    public static void main(String[] args) throws IOException, SQLException {
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
        substr();
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
            builder.append(category.toString()).append(",");
        }

        final String csvList = builder.toString();

        return csvList.substring(0, csvList.length() - 1); // remove last comma 
    }
    
    private static void unicode() {
        String[] characters = {
               "\u0009", // horizontal tab
               /*"\u000a", // new line*/
               /* "\u000d", // carriage return*/
        };

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
        }
        catch (Exception e) {
        }
    }

    private static void arrays() {
        String[] arr = new String[] { "hello" };

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
        }
        else {
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
        int value = 5%3;
        System.out.printf("1 %%="+value);
    }

    private static void log() {
        System.out.println("hello from main1");
        LOGGER.log(Level.WARNING, "Hello this is a warning message");
        LOGGER.log(Level.INFO, "Hello this is an info message");
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
            }
            else {
                System.out.println("DEBUG: " + o.toString());
            }
        }
    }
}
