package nl.hi.kuba.mocking;

public class Base {
    public final String[] publicFinalMethod() {
        System.out.println("Real publicFinalMethod was called...");

        return new String[]{"one", "two"};
    }
}
