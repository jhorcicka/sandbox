package nl.hi.kuba.logback;

public interface ISecond {
    default void methodA() {
        System.err.println("Second-methodA");
    }
}
