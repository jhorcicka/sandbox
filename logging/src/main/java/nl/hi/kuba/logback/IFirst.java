package nl.hi.kuba.logback;

public interface IFirst {
    default void methodA() {
        System.err.println("IFirst-methodA");
    }
}
