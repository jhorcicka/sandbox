package nl.hi.kuba;

public class Demo {
    private static String SUFFIX = "abc";

    public static void main(String[] args) {
        System.out.println("test");
    }

    private static String addPrefixAndSuffix(String input) {
        String prefix = "xyz";
        return prefix + input + SUFFIX;
    }
}
