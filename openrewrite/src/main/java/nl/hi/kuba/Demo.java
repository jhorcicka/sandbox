package nl.hi.kuba;

public class Demo {
    private static String suffix = "abc";

    public static void main(String[] args) {
        System.out.println("test");
    }

    private static String addPrefixAndSuffix(String input) {
        String prefix = "xyz";
        return prefix + input + suffix;
    }
}
