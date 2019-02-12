package cz.hk.gmc.logback;

public interface ISecond {
    default void methodA() {
        System.err.println("Second-methodA");
    }
}
