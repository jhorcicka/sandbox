package cz.hk.gmc.logback;

public class Program {
    public static void main(String[] args) {
        Comparable c1 = "Abc";
        Comparable c2 = new String("Abc");
        System.err.println("MYTODO: " + c1.equals(c2));
    }
}
