package cz.hk.gmc.logback;

public interface IFirst {
    default void methodA() {
        System.err.println("IFirst-methodA");
    }
}
